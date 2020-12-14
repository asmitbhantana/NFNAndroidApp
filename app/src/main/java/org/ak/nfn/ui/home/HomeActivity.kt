package org.ak.nfn.ui.home

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.ak.nfn.R

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var TAG = "HelloWorld"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.adminFragment, R.id.faqFragment, R.id.heatsGuidelineFragment,
                R.id.homeFragment, R.id.homeFragment, R.id.leaderFragment, R.id.logoutFragment,
                R.id.newsFragment, R.id.shareAppFragment, R.id.tellFriendFragment, R.id.termsFragment,
                R.id.transactionFragment
        ), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            val l =  destination.label as String
            when (l){
                "home_fragment" -> supportActionBar?.title = "Home"
                "faq_fragment" -> supportActionBar?.title = "FAQ"
                "transaction_fragment" -> supportActionBar?.title = "Transaction"
                "admin_fragment" -> supportActionBar?.title = "Admin Panel"
                "leader_fragment" -> supportActionBar?.title = "Leader Panel"
                "heats_guideline_fragment" -> supportActionBar?.title = "Heats Guideline"
                "tell_friend_fragment" -> supportActionBar?.title = "Tell Friend"
                "share_app_fragment" -> supportActionBar?.title = "Share App"
                "terms_fragment" -> supportActionBar?.title = "Terms & Conditions"
                "logout_fragment" -> supportActionBar?.title = "Logout"
                else -> {
//                    supportActionBar?.title = "Default"
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
//        NavigationUI.setupWithNavController(toolbar, navController)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        return NavigationUI.navigateUp(navController, drawerLayout)
        return navController.navigateUp(appBarConfiguration)
    }
}