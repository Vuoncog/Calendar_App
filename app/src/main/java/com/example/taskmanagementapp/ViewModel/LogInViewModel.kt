package com.example.taskmanagementapp.ViewModel

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
<<<<<<< HEAD
import androidx.lifecycle.ViewModel
=======
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
import com.example.taskmanagementapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

<<<<<<< HEAD
class LogInViewModel(val mainActivity: Activity?){
=======
class LogInViewModel(val mainActivity: Activity?) {
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)

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

<<<<<<< HEAD
    fun signUp(email: String, password: String, name: String, resultJob : (()-> Unit)? = null): FirebaseUser? {
=======
    fun signUp(email: String, password: String, name: String){
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
        val auth: FirebaseAuth = Firebase.auth
        if (isUserInfoValidate(email, password)) {
            if (isNetworkAvailable()) {
                if (name.isNotBlank()) {
                    // After checking network connection and validate user's information, sign up user
                    // with Firebase using email and password
<<<<<<< HEAD
                    val startTime = System.currentTimeMillis()
=======
                    var startTime = System.currentTimeMillis()
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mainActivity!!) { task ->
                            if (task.isSuccessful) {
                                val nameUpdate = userProfileChangeRequest {
                                    displayName = name
                                }
                                // Sign up success, return user just created and update User's full name
                                Firebase.auth.currentUser!!.updateProfile(nameUpdate)
                                Log.e("RESULT", "createUserWithEmail:success")
<<<<<<< HEAD
                                val endTime = System.currentTimeMillis()
=======
                                var endTime = System.currentTimeMillis()
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
                                Log.e("TIME", (endTime - startTime).toString())
                                Toast.makeText(
                                    mainActivity, R.string.sign_up_successfully,
                                    Toast.LENGTH_SHORT
                                ).show()
<<<<<<< HEAD
                                resultJob?.let {resultJob()}
=======
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
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
<<<<<<< HEAD
        return Firebase.auth.currentUser
=======
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
    }

    fun getCurrentUser(): FirebaseUser? {

        // Return the current user
        val user = Firebase.auth.currentUser
        user?.let { Log.e("UID", it.uid) }
        return user
    }

<<<<<<< HEAD
    fun signIn(email: String, password: String, resultJob : (()-> Unit)? = null){
=======
    fun signIn(email: String, password: String){
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
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
<<<<<<< HEAD

=======
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
                            Toast.makeText(
                                mainActivity,
                                R.string.sign_in_successfully,
                                Toast.LENGTH_SHORT
                            ).show()
<<<<<<< HEAD
                            resultJob?.let { resultJob() }
=======
>>>>>>> 9318657 (Sign In and Sign Up by Custom Email)
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