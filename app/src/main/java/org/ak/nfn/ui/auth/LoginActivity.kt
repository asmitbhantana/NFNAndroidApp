package org.ak.nfn.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import org.ak.nfn.R
import org.ak.nfn.databinding.ActivityLoginBinding
import org.ak.nfn.utils.hide
import org.ak.nfn.utils.show
import org.ak.nfn.utils.toast

class LoginActivity : AppCompatActivity(),AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        toast("Login Started")
        loading_progressbar_id.show()
    }

    override fun onFailure(message: String) {
        toast("Login Failure")
        loading_progressbar_id.hide()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loading_progressbar_id.hide()
        loginResponse.observe(this, Observer {
            toast(it.toString())
        })
    }
}