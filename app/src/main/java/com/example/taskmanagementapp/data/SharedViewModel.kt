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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagementapp.FacebookActivity
import com.example.taskmanagementapp.MainActivity
import com.example.taskmanagementapp.R
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
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

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
    private var mResultJob : ()->Unit = {}
    var navigateToLogin : ()->Unit = {}

    @Suppress("SENSELESS_COMPARISON")
    fun getSelectedDate(date: Date) {
        _allTaskInDate.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getSelectedRow(dateSelected = date).collect(
                    collector = {
                        val noteValue = if (it != null) it
                        else Note(date = date)
                        _allTaskInDate.value = RequestState.Success(noteValue)
                    }
                )
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

    fun signUp(email: String, password: String, name: String, resultJob : (()-> Unit)? = null): FirebaseUser? {
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
                                    mainActivity, R.string.sign_up_successfully,
                                    Toast.LENGTH_SHORT
                                ).show()
                                resultJob?.let {resultJob()}
                            } else {
                                // If sign up fails, display a message to the user.
                                Log.e("RESULT", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    mainActivity, task.exception?.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(mainActivity, R.string.input_all_field, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    mainActivity,
                    R.string.network_unavailable,
                    Toast.LENGTH_SHORT
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

    fun signIn(email: String, password: String, resultJob : (()-> Unit)? = null){
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
                                mainActivity,
                                R.string.sign_in_successfully,
                                Toast.LENGTH_SHORT
                            ).show()
                            resultJob?.let { resultJob() }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("RESULT", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                mainActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    mainActivity,
                    R.string.network_unavailable,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun signOut() {
        Firebase.auth.signOut()
    }

    fun signInGoogle(resultJob : ()->Unit){
        mResultJob = resultJob
        if(isNetworkAvailable())
        {
            val signInRequest = GetSignInIntentRequest.builder()
                .setServerClientId(mainActivity.getString(R.string.web_client_id))
                .build()

            signInClient.getSignInIntent(signInRequest)
                .addOnSuccessListener { pendingIntent ->
                    launchSignIn(pendingIntent)
                }
                .addOnFailureListener { e ->
                    Log.e("INTENT_ERROR", "Google Sign-in failed", e)
                }
        }
        else
            Toast.makeText(mainActivity, R.string.network_unavailable, Toast.LENGTH_SHORT).show()
    }

    private fun launchSignIn(pendingIntent: PendingIntent) {
        try {
            val intentSenderRequest = IntentSenderRequest.Builder(pendingIntent)
                .build()
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
        auth.signInWithCredential(credential)
            .addOnCompleteListener(mainActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.e("GOOGLE_SIGNIN_RESULT", "signInWithCredential:success")
                    Toast.makeText(
                        mainActivity,
                        R.string.sign_in_successfully,
                        Toast.LENGTH_SHORT
                    ).show()
                    mResultJob()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        mainActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("GOOGLE_SIGNIN_RESULT", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun signInFacebook(){
        openFacebookActivity()
    }

    private fun openFacebookActivity(){
        val intent = Intent(mainActivity, FacebookActivity::class.java)
        mainActivity.startActivity(intent)
        mainActivity.finish()
    }
}