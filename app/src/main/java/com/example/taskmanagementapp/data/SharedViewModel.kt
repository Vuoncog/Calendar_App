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
import androidx.lifecycle.viewModelScope
import com.example.taskmanagementapp.FacebookActivity
import com.example.taskmanagementapp.MainActivity
import com.example.taskmanagementapp.R
import com.example.taskmanagementapp.constant.EventInfo
import com.example.taskmanagementapp.constant.RequestState
import com.example.taskmanagementapp.database.Note
import com.example.taskmanagementapp.database.Repository
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    lateinit var mainActivity: MainActivity
    private val _allTaskInDate = MutableStateFlow<RequestState<Note>>(RequestState.Idle)
    val allTaskInDate: StateFlow<RequestState<Note>> = _allTaskInDate
    private val signInClient by lazy { Identity.getSignInClient(mainActivity) }
    private val auth = Firebase.auth
    private var mResultJob: () -> Unit = {}
    var navigateToLogin: () -> Unit = {}
    val titleAndDetailEvent = mutableStateOf(Pair("", ""))
    var startAndEndEvent = mutableStateOf(Pair(0L, 0L))
    val listEventsResult = mutableStateListOf<EventInfo>()
    val listBackgroundColor = listOf(0xFFF8F2F3, 0xFFFEE6DF, 0xFFFAA36A, 0xFF03DAC5, 0xFFBB86FC)
    val database =
        Firebase.database("https://todoapp-368e2-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
    lateinit var oldEventInfo: EventInfo
    var dateOfEvent = LocalDate.now().toEpochDay()

    @Suppress("SENSELESS_COMPARISON")
    fun getSelectedDate(date: Date) {
        _allTaskInDate.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getSelectedRow(dateSelected = date).collect(collector = {
                    val noteValue = if (it != null) it
                    else Note(date = date)
                    _allTaskInDate.value = RequestState.Success(noteValue)
                })
            }
        } catch (e: java.lang.Exception) {
            _allTaskInDate.value = RequestState.Error(e)
        }
    }

    // Check network connection, used for Sign In and Sign Up
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

    fun signOut() {
        Firebase.auth.signOut()
    }

    fun signInGoogle(resultJob: () -> Unit) {
        mResultJob = resultJob
        if (isNetworkAvailable()) {
            val signInRequest = GetSignInIntentRequest.builder()
                .setServerClientId(mainActivity.getString(R.string.web_client_id)).build()

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

    @Suppress("UNCHECKED_CAST")
    fun addEventInfo(onFinished: () -> Unit) {
        if (isNetworkAvailable()) {
            val title = titleAndDetailEvent.value.first
            if (title == "") {
                Toast.makeText(mainActivity, "Please Input Title", Toast.LENGTH_SHORT).show()
            } else {
                val detail = titleAndDetailEvent.value.second
                val startTime = startAndEndEvent.value.first
                val endTime = startAndEndEvent.value.second
                val listEvents = mutableListOf<EventInfo>()
                val currentEpochDate = LocalDate.now().toEpochDay().toString()
                val mDatabaseReference = database.child(
                    getCurrentUser()?.uid.toString()
                ).child(currentEpochDate).child("ListEvent")
                mDatabaseReference.get().addOnCompleteListener {
                    if (it.result != null) {
                        for (mEventInfo in it.result.children) {
                            val value = mEventInfo.getValue<EventInfo>()
                            listEvents.add(value!!)
                        }
                    }
                    listEvents.add(
                        EventInfo(
                            color = listBackgroundColor.random(),
                            title = title,
                            detail = detail,
                            startTime = startTime,
                            endTime = endTime,
                        )
                    )
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
                    onFinished()
                }
            }
        } else {
            Toast.makeText(mainActivity, "Network is disconnected!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getEventInfo(date: Long, isCalendarContent: Boolean) {
        if (isCalendarContent) {
            if (isNetworkAvailable()) {
                listEventsResult.clear()
                val childEventListenr = object : ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val eventInfo = snapshot.getValue<EventInfo>()
                        Log.e("TITLE", eventInfo!!.title)
                        listEventsResult.add(eventInfo)
                    }

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                        /*if(listEventsResult.size > 0 && listEventsResult.indexOf(oldEventInfo) >= 0){
                            val eventInfo = snapshot.getValue<EventInfo>()
                            listEventsResult.set(listEventsResult.indexOf(oldEventInfo),eventInfo!!)
                        }*/
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {}

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                    override fun onCancelled(error: DatabaseError) {}
                }
                database.child(
                    getCurrentUser()?.uid.toString()
                ).child(date.toString()).child("ListEvent").addChildEventListener(childEventListenr)
            } else {
                Toast.makeText(
                    mainActivity,
                    "Load data unsuccessfully because network is disconnected!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun getHourAndMinute(time: Long): Float {
        val mTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(time),
            ZoneId.systemDefault()
        )
        val hour = mTime.hour
        val minute =
            (mTime.minute.toDouble() / 60).toBigDecimal().setScale(1, RoundingMode.HALF_DOWN)
                .toDouble()
        val result = hour + minute
        return result.toFloat()
    }

    fun updateEventInfo(eventInfo: EventInfo, onFinished: () -> Unit) {
        val index = listEventsResult.indexOf(oldEventInfo)
        database.child(
            getCurrentUser()?.uid.toString()
        ).child(dateOfEvent.toString()).child("ListEvent").child(index.toString())
            .setValue(eventInfo).addOnSuccessListener {
            Toast.makeText(mainActivity, "Update Event Successfully!", Toast.LENGTH_SHORT)
                .show()
            onFinished()
        }
    }
}
