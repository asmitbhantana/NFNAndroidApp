package org.ak.nfn.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import org.ak.nfn.data.db.entities.UserToken
import org.ak.nfn.data.repository.UserRepository
import org.ak.nfn.utils.ApiException
import org.ak.nfn.utils.Coroutines
import org.ak.nfn.utils.NoInternetException

val TAG = "Auth"
class AuthViewModel(
    private val userRepository: UserRepository
):ViewModel() {

    var email: String?=null
    var password: String?=null

    var authListener:AuthListener? = null

    fun getLoggedInUserToken() = userRepository.getUser()

    fun onLoginButtonClicked(view: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListener?.onFailure("Invalid email or password")
            return
        }else{
            //success
            Coroutines.main {
                try {
                    val authResponse = userRepository.userLogin(email!!,password!!)
                    authResponse.token?.let{
                        authListener?.onSuccess(it)
                        //save the token to user
                        val token = UserToken(token = it)
                        userRepository.saveToken(token)
                        return@main
                    }
                    authListener?.onFailure(authResponse.token!!)
                    Log.d(TAG, "onLoginButtonClicked: "+ authResponse)
                }
                catch (e:ApiException){
                    authListener?.onFailure(e.message!!)
                }
                catch (e:NoInternetException){
                    authListener?.onFailure(e.message!!)
                }


            }
        }
        

    }
}