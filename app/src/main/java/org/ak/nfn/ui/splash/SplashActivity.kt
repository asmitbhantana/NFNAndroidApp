package org.ak.nfn.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.lifecycle.ViewModelProvider
import org.ak.nfn.R
import org.ak.nfn.ui.auth.AuthViewModel
import org.ak.nfn.ui.auth.AuthViewModelFactory
import org.ak.nfn.ui.home.HomeActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import androidx.lifecycle.Observer
import org.ak.nfn.ui.onboard.OnboardActivity


class SplashActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by lazy { (applicationContext as KodeinAware).kodein }
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        SystemClock.sleep(1000)

        viewModel.getLoggedInUserToken().observe(this, Observer { user->
            if(user!=null){
                Intent(this, HomeActivity::class.java).also{
//                Intent(this, OnboardActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else {
                Intent(this, OnboardActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }
}