package org.ak.nfn.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import org.ak.nfn.R
import org.ak.nfn.data.db.AppDatabase
import org.ak.nfn.data.network.MyApi
import org.ak.nfn.data.network.NetworkConnectionInterceptor
import org.ak.nfn.data.repository.UserRepository
import org.ak.nfn.databinding.ActivityLoginBinding
import org.ak.nfn.ui.home.HomeActivity
import org.ak.nfn.utils.hide
import org.ak.nfn.utils.show
import org.ak.nfn.utils.snackbar

class LoginActivity : AppCompatActivity(),AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val database = AppDatabase(this)
        val repository = UserRepository(api, database)
        val factory = AuthViewModelFactory(repository)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUserToken().observe(this, Observer { user->
            if(user!=null){
                Intent(this, HomeActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
        loading_progressbar_id.show()
    }

    override fun onFailure(message: String) {
        root_layout_id.snackbar(message)
        loading_progressbar_id.hide()
    }

    override fun onSuccess(userToken: String) {
        loading_progressbar_id.hide()
//        root_layout_id.snackbar("Login Success!")
    }
}