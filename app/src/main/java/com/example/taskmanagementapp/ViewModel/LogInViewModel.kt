package com.example.taskmanagementapp.ViewModel

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import com.example.taskmanagementapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class LogInViewModel(val mainActivity: Activity?) {

    // Check network connection, used for Sign In and Sign Up
    fun isNetworkAvailable(): Boolean {
        mainActivity?.let {
            val connectivityManager: ConnectivityManager =
                it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            return (activeNetworkInfo != null) && (activeNetworkInfo.isConnected)
        }
        return false
    }

    fun isUserInfoValidate(email: String, password: String): Boolean {
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

    fun signUp(email: String, password: String, name: String){
        val auth: FirebaseAuth = Firebase.auth
        if (isUserInfoValidate(email, password)) {
            if (isNetworkAvailable()) {
                if (name.isNotBlank()) {
                    // After checking network connection and validate user's information, sign up user
                    // with Firebase using email and password
                    var startTime = System.currentTimeMillis()
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mainActivity!!) { task ->
                            if (task.isSuccessful) {
                                val nameUpdate = userProfileChangeRequest {
                                    displayName = name
                                }
                                // Sign up success, return user just created and update User's full name
                                Firebase.auth.currentUser!!.updateProfile(nameUpdate)
                                Log.e("RESULT", "createUserWithEmail:success")
                                var endTime = System.currentTimeMillis()
                                Log.e("TIME", (endTime - startTime).toString())
                                Toast.makeText(
                                    mainActivity, R.string.sign_up_successfully,
                                    Toast.LENGTH_SHORT
                                ).show()
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
    }

    fun getCurrentUser(): FirebaseUser? {

        // Return the current user
        val user = Firebase.auth.currentUser
        user?.let { Log.e("UID", it.uid) }
        return user
    }

    fun signIn(email: String, password: String){
        val auth: FirebaseAuth = Firebase.auth
        if (isUserInfoValidate(email, password)) {
            if (isNetworkAvailable()) {
                // After checking network connection and validate user's information, authenticate
                // with Firebase using email and password
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(mainActivity!!) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("RESULT", "signInWithEmail:success")
                            Toast.makeText(
                                mainActivity,
                                R.string.sign_in_successfully,
                                Toast.LENGTH_SHORT
                            ).show()
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
}