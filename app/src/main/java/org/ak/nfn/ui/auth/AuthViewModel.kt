package org.ak.nfn.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel:ViewModel() {

    var email: String?=null
    var password: String?=null

    fun onLoginButtonClicked(view: View){
        if (email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            return
        }else{
            //success
            return
        }
    }
}