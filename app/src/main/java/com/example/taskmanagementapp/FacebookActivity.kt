package com.example.taskmanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FacebookActivity : AppCompatActivity() {
    private val callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook)

        val logInManager = LoginManager.getInstance()

        logInManager.logOut()
        logInManager.logInWithReadPermissions(this, listOf("email", "public_profile", "user_friends"))
        logInManager.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Log.e("RESULT", "onCancel")
                }

                override fun onError(error: FacebookException) {
                    // App code
                    Log.e("RESULT", "onError")
                    error.localizedMessage?.let { Log.e("ERROR", it) }
                }

                override fun onSuccess(result: LoginResult) {
                    Log.e("RESULT", "onSuccess")
                    Log.e("TOKEN", result.accessToken.toString())
                    handleFacebookAccessToken(result.accessToken)
                }
            })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val auth = Firebase.auth
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    /*val accessToken = AccessToken.getCurrentAccessToken()
                    val request = GraphRequest.newMeRequest(accessToken){ obj, _ ->
                        obj?.getString("email")?.let{
                            Firebase.auth.currentUser?.updateEmail(it)
                        }
                    }
                    val parameters = Bundle()
                    parameters.putString("field", "link,type(large),email")
                    request.parameters = parameters
                    request.executeAsync()*/
                    openMainActivity()
                    Toast.makeText(this, getString(R.string.sign_in_successfully), Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("RESULT", "signInWithCredential:failure", task.exception)
                    openMainActivity()
                    Toast.makeText(this, task.exception!!.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}