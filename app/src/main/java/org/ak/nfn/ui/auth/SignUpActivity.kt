package org.ak.nfn.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.ak.nfn.R
import org.ak.nfn.ui.home.HomeActivity
import org.ak.nfn.utils.snackbar
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class  SignUpActivity : AppCompatActivity(), KodeinAware  {

    override val kodein: Kodein by lazy { (applicationContext as KodeinAware).kodein }

    private val factory:AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
//
//        viewModel.getSignedUpUser().observe(this, Observer { user->
//            if(user!=null){
//                Intent(this, LoginActivity::class.java).also{
//                    it.putExtra("Signup",1)
//                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(it)
//                }
//            }
//        })
    }
}