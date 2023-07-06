package com.example.taskmanagementapp.data

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.taskmanagementapp.FacebookActivity
import com.example.taskmanagementapp.MainActivity
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.*
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    lateinit var mainActivity: MainActivity
    private val signInClient by lazy { Identity.getSignInClient(mainActivity) }
    private val auth = Firebase.auth
    private var mResultJob: () -> Unit = {}
    var navigateToLogin: () -> Unit = {}
    val titleAndDetail = mutableStateOf(Pair("", ""))
    var startAndEnd = mutableStateOf(Pair(0L, 0L))
    val listEventResult = mutableStateListOf<EventInfo>()
    val listTaskResult = mutableStateListOf<ToDoTask>()
    val database =
        Firebase.database("https://todoapp-368e2-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
    var oldEventInfo = EventInfo()
    var oldTaskInfo = ToDoTask()
    var dateOfEvent = LocalDate.now().toEpochDay()
    var dateOfTask = LocalDate.now().toEpochDay()
    val listSubTasks = mutableStateListOf<SubTask>()
    var eventTheme = 0UL
    var taskSticker = 0
    var eventSticker = 0
    var minusTime = 0

    // Check network connection
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager =
            mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetworkInfo != null) && (activeNetworkInfo.isConnected)
    }

    private fun isUserInfoValidate(email: String, password: String): Boolean {
        var result = true
        if (email.isNotBlank() && password.isNotBlank()) {

            // Check if input is email pattern or not
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(mainActivity, R.string.invalidate_mail, Toast.LENGTH_SHORT).show()
                result = false
            }

            // Check the condition of password
            if (password.length < 6) {
                Toast.makeText(mainActivity, R.string.invalidate_password, Toast.LENGTH_SHORT)
                    .show()
                result = false
            }
        }

        // Email or password or both are blank, display a message to user
        else {
            Toast.makeText(mainActivity, R.string.input_all_field, Toast.LENGTH_SHORT).show()
            result = false
        }
        return result
    }

    fun signUp(
        email: String, password: String, name: String, resultJob: (() -> Unit)? = null
    ): FirebaseUser? {
        val auth: FirebaseAuth = Firebase.auth
        if (isUserInfoValidate(email, password)) {
            // Check if network is available
            if (isNetworkAvailable()) {
                if (name.isNotBlank()) {
                    // After checking network connection and validate user's information, sign up user
                    // with Firebase using email and password
                    val startTime = System.currentTimeMillis()
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mainActivity) { task ->
                            if (task.isSuccessful) {
                                val nameUpdate = userProfileChangeRequest {
                                    displayName = name
                                }
                                // Sign up success, return user just created and update User's full name
                                Firebase.auth.currentUser!!.updateProfile(nameUpdate)
                                Log.e("RESULT", "createUserWithEmail:success")
                                val endTime = System.currentTimeMillis()
                                Log.e("TIME", (endTime - startTime).toString())
                                Toast.makeText(
                                    mainActivity, R.string.sign_up_successfully, Toast.LENGTH_SHORT
                                ).show()
                                resultJob?.let { resultJob() }
                            } else {
                                // If sign up fails, display a message to the user.
                                Log.e("RESULT", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    mainActivity, task.exception?.message, Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(mainActivity, R.string.input_all_field, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    mainActivity, R.string.network_unavailable, Toast.LENGTH_SHORT
                ).show()
            }
        }
        return Firebase.auth.currentUser
    }

    fun getCurrentUser(): FirebaseUser? {

        // Return the current user
        val user = Firebase.auth.currentUser
        user?.let { it.email?.let { it1 -> Log.e("EMAIL", it1) } }
        return user
    }

    fun signIn(email: String, password: String, resultJob: (() -> Unit)? = null) {
        val auth: FirebaseAuth = Firebase.auth
        if (isUserInfoValidate(email, password)) {
            // Check if network is available
            if (isNetworkAvailable()) {
                // After checking network connection and validate user's information, authenticate
                // with Firebase using email and password
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(mainActivity) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("RESULT", "signInWithEmail:success")

                            Toast.makeText(
                                mainActivity, R.string.sign_in_successfully, Toast.LENGTH_SHORT
                            ).show()
                            resultJob?.let { resultJob() }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("RESULT", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                mainActivity, "Authentication failed.", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    mainActivity, R.string.network_unavailable, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    //Sign Out
    fun signOut() {
        Firebase.auth.signOut()
    }


    // Sign in through Gmail
    fun signInGoogle(resultJob: () -> Unit) {
        mResultJob = resultJob
        // Check if network is available
        if (isNetworkAvailable()) {
            // Authenticate with Firebase by App's ID
            val signInRequest = GetSignInIntentRequest.builder()
                .setServerClientId(mainActivity.getString(R.string.web_client_id)).build()

            // After authenticating successful, execute launchSignIn function
            signInClient.getSignInIntent(signInRequest).addOnSuccessListener { pendingIntent ->
                launchSignIn(pendingIntent)
            }.addOnFailureListener { e ->
                Log.e("INTENT_ERROR", "Google Sign-in failed", e)
            }
        } else Toast.makeText(mainActivity, R.string.network_unavailable, Toast.LENGTH_SHORT).show()
    }

    private fun launchSignIn(pendingIntent: PendingIntent) {
        try {
            val intentSenderRequest = IntentSenderRequest.Builder(pendingIntent).build()
            mainActivity.getSignInLauncher().launch(intentSenderRequest)
        } catch (e: IntentSender.SendIntentException) {
            Log.e("SIGNIN_ERROR", "Couldn't start Sign In: ${e.localizedMessage}")
        }
    }

    fun handleSignInResult(data: Intent?) {
        // Result returned from launching the Sign In PendingIntent
        try {
            // Google Sign In was successful, authenticate with Firebase
            val credential = signInClient.getSignInCredentialFromIntent(data)
            val idToken = credential.googleIdToken
            if (idToken != null) {
                Log.e("CREDENTIAL_ID", "firebaseAuthWithGoogle: ${credential.id}")
                firebaseAuthWithGoogle(idToken)
            } else {
                // Shouldn't happen.
                Log.e("NO_ID", "No ID token!")
            }
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Log.e("GOOGLE_SIGNIN_FAILED", "Google sign in failed", e)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(mainActivity) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.e("GOOGLE_SIGNIN_RESULT", "signInWithCredential:success")
                Toast.makeText(
                    mainActivity, R.string.sign_in_successfully, Toast.LENGTH_SHORT
                ).show()
                mResultJob()
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(
                    mainActivity, "Authentication failed.", Toast.LENGTH_SHORT
                ).show()
                Log.e("GOOGLE_SIGNIN_RESULT", "signInWithCredential:failure", task.exception)
            }
        }
    }

    fun signInFacebook() {
        openFacebookActivity()
    }

    private fun openFacebookActivity() {
        val intent = Intent(mainActivity, FacebookActivity::class.java)
        mainActivity.startActivity(intent)
        mainActivity.finish()
    }


    // Convert Event's time into Float Value to show it on UI
    fun getHourAndMinute(time: Long): Float {
        val mTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(time),
            ZoneId.systemDefault()
        )
        val hour = mTime.hour
        // Convert Minute Value to Hour Value and round to 1 decimal
        val minute =
            (mTime.minute.toDouble() / 60).toBigDecimal().setScale(1, RoundingMode.HALF_DOWN)
                .toDouble()
        val result = hour + minute
        return result.toFloat()
    }

    suspend fun updateEventInfo(onFinished: () -> Unit) {
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                // Get the index of current Event in listEventResult, it is also it's index in Firebase List
                val index = listEventResult.indexOf(oldEventInfo)
                val newEvent = EventInfo(
                    title = titleAndDetail.value.first,
                    detail = titleAndDetail.value.second,
                    startTime = startAndEnd.value.first,
                    endTime = startAndEnd.value.second,
                    color = eventTheme.toString(),
                    sticker = eventSticker
                )
                // Move to the position of the event we need and set the new value for it
                database.child(
                    getCurrentUser()?.uid.toString()
                ).child(dateOfEvent.toString()).child("ListEvent").child(index.toString())
                    .setValue(newEvent).addOnSuccessListener {
                        Toast.makeText(
                            mainActivity,
                            "Update Event Successfully!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        // Do the next step
                        onFinished()
                    }
            }
        } else {
            Toast.makeText(mainActivity, "Network is disconnected!", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun updateDoneTask(oldTask: ToDoTask, newTask: ToDoTask) {
        if (isNetworkAvailable()) {
            withContext(Dispatchers.IO) {
                val index = listTaskResult.indexOf(oldTask)
                listTaskResult[index] = newTask
                database.child(
                    getCurrentUser()?.uid.toString()
                ).child(dateOfTask.toString()).child("ListTask").child(index.toString())
                    .setValue(newTask)
            }
        } else {
            Toast.makeText(
                mainActivity,
                "Network is disconnected so we can not save latest changes!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    suspend fun updateToDoTask(onFinished: () -> Unit) {
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                val newTask = ToDoTask(
                    taskType = TaskType(
                        icon = taskSticker,
                        description = titleAndDetail.value.second
                    ),
                    taskName = titleAndDetail.value.first,
                    isDone = oldTaskInfo.getDone(),
                    time = startAndEnd.value.first,
                    listSubTasks = listSubTasks
                )
                // Get the index of current Event in listEventResult, it is also it's index in Firebase List
                val index = listTaskResult.indexOf(oldTaskInfo)
                // Move to the position of the event we need and set the new value for it
                database.child(
                    getCurrentUser()?.uid.toString()
                ).child(dateOfTask.toString()).child("ListTask").child(index.toString())
                    .setValue(newTask).addOnSuccessListener {
                        Toast.makeText(
                            mainActivity,
                            "Update Task Successfully!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        // Do the next step
                        onFinished()
                    }
            }
        } else {
            Toast.makeText(mainActivity, "Network is disconnected!", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun removeEvent(onFinished: () -> Unit) {
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                //Remove the event which we want in our list
                listEventResult.remove(oldEventInfo)
                //Notify to user if delete successfully, show Toast by UI Thread
                mainActivity.runOnUiThread {
                    Toast.makeText(mainActivity, "Delete Event Successfully!", Toast.LENGTH_SHORT)
                        .show()
                }
                // Back To Calendar Content
                onFinished()
                // Update new list to Firebase
                val mDatabaseReference = database.child(
                    getCurrentUser()?.uid.toString()
                ).child(dateOfEvent.toString()).child("ListEvent")
                mDatabaseReference.removeValue()
                listEventResult.forEach { event ->
                    mDatabaseReference.child(listEventResult.indexOf(event).toString())
                        .setValue(event)
                }
            }
        } else {
            Toast.makeText(
                mainActivity,
                "Network is disconnected so we can not save latest changes!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    suspend fun removeToDoTask(onFinished: () -> Unit) {
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                //Remove the task which we want in our list
                listTaskResult.remove(oldTaskInfo)
                //Notify to user if delete successfully, show Toast by UI Thread
                mainActivity.runOnUiThread {
                    Toast.makeText(mainActivity, "Delete Task Successfully!", Toast.LENGTH_SHORT)
                        .show()
                }
                // Back To Management Content
                onFinished()
                // Update new list to Firebase
                val mDatabaseReference = database.child(
                    getCurrentUser()?.uid.toString()
                ).child(dateOfTask.toString()).child("ListTask")
                mDatabaseReference.removeValue()
                listTaskResult.forEach { task ->
                    mDatabaseReference.child(listTaskResult.indexOf(task).toString()).setValue(task)
                }
            }
        } else {
            Toast.makeText(
                mainActivity,
                "Network is disconnected so we can not save latest changes!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    suspend fun addEventInfo(onFinished: () -> Unit) {
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                // Check if the title text field is empty, app will notice to user
                val title = titleAndDetail.value.first
                if (title == "") {
                    mainActivity.runOnUiThread {
                        Toast.makeText(mainActivity, "Please Input Title", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    // Get Event's information
                    val detail = titleAndDetail.value.second
                    val startTime = startAndEnd.value.first
                    val endTime = startAndEnd.value.second
                    val listEvents = mutableListOf<EventInfo>()

                    // Move to the position we need
                    val mDatabaseReference = database.child(
                        getCurrentUser()?.uid.toString()
                    ).child(dateOfEvent.toString()).child("ListEvent")
                    mDatabaseReference.get().addOnCompleteListener {
                        if (it.result != null) {
                            // Get all of elements at this Firebase's position and add them to a list
                            for (mEventInfo in it.result.children) {
                                val value = mEventInfo.getValue<EventInfo>()
                                listEvents.add(value!!)
                            }
                        }
                        // Add new Event to the list
                        listEvents.add(
                            EventInfo(
                                color = eventTheme.toString(),
                                title = title,
                                detail = detail,
                                startTime = startTime,
                                endTime = endTime,
                                sticker = eventSticker
                            )
                        )
                        // Push new list to Firebase
                        for (mEventInfo in listEvents) {
                            mDatabaseReference.child(listEvents.indexOf(mEventInfo).toString())
                                .setValue(mEventInfo)
                                .addOnCanceledListener {
                                    Log.e("PUSH_DATABASE", it.exception.toString())
                                    Toast.makeText(
                                        mainActivity,
                                        "Add Event Failed!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                        }
                        Toast.makeText(mainActivity, "Add Event Successfully!", Toast.LENGTH_SHORT)
                            .show()
                        // Do the next step
                        onFinished()
                    }
                }
            }
        } else {
            Toast.makeText(mainActivity, "Network is disconnected!", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun addToDoTask(onFinished: () -> Unit) {
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                val title = titleAndDetail.value.first
                // Check if the title text field is empty, app will notice to user
                if (title == "") {
                    mainActivity.runOnUiThread {
                        Toast.makeText(mainActivity, "Please Input Title", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    val listTasks = mutableListOf<ToDoTask>()
                    // Move to the position we need
                    val mDatabaseReference = database.child(
                        getCurrentUser()?.uid.toString()
                    ).child(dateOfTask.toString()).child("ListTask")
                    mDatabaseReference.get().addOnCompleteListener {
                        if (it.result != null) {
                            // Get all of elements at this Firebase's position and add them to a list
                            for (mToDoTask in it.result.children) {
                                val value = mToDoTask.getValue<ToDoTask>()
                                listTasks.add(value!!)
                            }
                        }
                        // Add new task to the list
                        listTasks.add(
                            ToDoTask(
                                taskName = title,
                                isDone = false,
                                time = startAndEnd.value.first,
                                taskType = TaskType(
                                    taskSticker,
                                    titleAndDetail.value.second
                                ),
                                listSubTasks = listSubTasks as List<SubTask>
                            )
                        )
                        // Push new list to Firebase
                        for (mTask in listTasks) {
                            mDatabaseReference.child(listTasks.indexOf(mTask).toString())
                                .setValue(mTask)
                                .addOnCanceledListener {
                                    Log.e("PUSH_DATABASE", it.exception.toString())
                                    Toast.makeText(
                                        mainActivity,
                                        "Add Task Failed!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                        }
                        Toast.makeText(mainActivity, "Add Task Successfully!", Toast.LENGTH_SHORT)
                            .show()
                        // Do the next step
                        onFinished()
                    }
                }
            }
        } else {
            Toast.makeText(mainActivity, "Network is disconnected!", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun getEventInfo() {
        // This function's only used at CalendarContent
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                // Clear the listEventResult whenever the getEventInfo is callback
                listEventResult.clear()
                val childEventListener = object : ChildEventListener {
                    override fun onChildAdded(
                        // This function get all child of this path and triggered again
                        // whenever a new child is added to this path
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        val eventInfo = snapshot.getValue<EventInfo>()
                        // Add the value we got from Firebase into the listEventResult
                        val index = listEventResult.indexOf(eventInfo)
                        if(index < 0) {
                            listEventResult.add(eventInfo!!)
                        }
                    }


                    // This function triggered whenever a child of the path is changed
                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        val eventInfo = snapshot.getValue<EventInfo>()
                        val index = listEventResult.indexOf(oldEventInfo)
                        if(index >= 0){
                            listEventResult[index] = eventInfo!!
                        }
                    }

                    // This function triggered whenever a child of the path is removed
                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        val eventInfo = snapshot.getValue<EventInfo>()
                        listEventResult.remove(eventInfo)
                    }

                    override fun onChildMoved(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                    }

                    override fun onCancelled(error: DatabaseError) {}
                }
                // Apply ChildEventListener to the path we want
                database.child(
                    getCurrentUser()?.uid.toString()
                ).child(dateOfEvent.toString()).child("ListEvent")
                    .addChildEventListener(childEventListener)
            }
        } else {
            Toast.makeText(
                mainActivity,
                "Load data unsuccessfully because network is disconnected!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    suspend fun getToDoTask() {
        // Check if network is available
        if (isNetworkAvailable()) {
            // Handle interacting with Firebase on IO Thread to offload for Main Thread
            withContext(Dispatchers.IO) {
                // Clear the listTaskResult whenever the getToDoTask is callback
                listTaskResult.clear()
                val childEventListener = object : ChildEventListener {
                    override fun onChildAdded(
                        // This function get all child of this path and triggered again
                        // whenever a new child is added to this path
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        val toDoTask = snapshot.getValue<ToDoTask>()
                        val index = listTaskResult.indexOf(toDoTask)
                        if(index < 0) {
                            // Add the value we got from Firebase into the listTaskResult
                            listTaskResult.add(toDoTask!!)
                        }
                    }

                    // This function triggered whenever a child of the path is changed
                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        val toDoTask = snapshot.getValue<ToDoTask>()
                        val index = listTaskResult.indexOf(oldTaskInfo)
                        if(index >= 0)
                            listTaskResult[index] = toDoTask!!
                    }

                    // This function triggered whenever a child of the path is removed
                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        val toDoTask = snapshot.getValue<ToDoTask>()
                        listTaskResult.remove(toDoTask)
                    }

                    override fun onChildMoved(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                    }

                    override fun onCancelled(error: DatabaseError) {}
                }
                // Apply ChildEventListener to the path we want
                database.child(
                    getCurrentUser()?.uid.toString()
                ).child(dateOfTask.toString()).child("ListTask")
                    .addChildEventListener(childEventListener)
            }
        } else {
            Toast.makeText(
                mainActivity,
                "Load data unsuccessfully because network is disconnected!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
