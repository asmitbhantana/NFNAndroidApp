package org.ak.nfn.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.ak.nfn.R

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


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
        ))

        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}