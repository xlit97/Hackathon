package com.futureyakutia.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottom_nav.setOnNavigationItemSelectedListener {
            if (bottom_nav.selectedItemId != it.itemId) {
                when(it.itemId) {
                    R.id.menu_home -> navController.navigate(R.id.go_to_home)
                    R.id.menu_chat -> navController.navigate(R.id.go_to_help)
                    R.id.menu_invest -> navController.navigate(R.id.go_to_investigate)
                    R.id.menu_package -> navController.navigate(R.id.go_to_package)
                }
            }
            true
        }
    }
}
