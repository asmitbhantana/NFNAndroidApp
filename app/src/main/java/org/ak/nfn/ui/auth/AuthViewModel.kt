package org.ak.nfn.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import org.ak.nfn.data.repository.UserRepository
import org.ak.nfn.utils.Coroutines
val TAG = "Auth"
class AuthViewModel:ViewModel() {

    var email: String?=null
    var password: String?=null

    var authListener:AuthListener? = null

    fun onLoginButtonClicked(view: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListener?.onFailure("Invalid email or password")
            return
        }else{
            //success
            Coroutines.main {
                val response = UserRepository().userLogin(email!!,password!!)

                Log.d(TAG, "onLoginButtonClicked: "+response)
                if (response.isSuccessful){
                    Log.d(TAG, "onLoginButtonClicked: ${response.body()}")
                    authListener?.onSuccess(response.body()?.token!!)
                }else{
                    authListener?.onFailure("Error code: ${response.code()}")
                }
            }
        }
        

    }
}