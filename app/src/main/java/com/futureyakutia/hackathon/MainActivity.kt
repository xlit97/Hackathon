package com.futureyakutia.hackathon

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_WRITE = 10
    }

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
        if (checkWritePermission()) {
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE);
        }
        plus.setOnClickListener {
            buttons_layout.isVisible = !buttons_layout.isVisible
            if (buttons_layout.isVisible) {
                plus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_plus_close))
            } else {
                plus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_plus))
            }
        }
        report_button.setOnClickListener {
            buttons_layout.isVisible = false
            plus.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_plus))
            navController.navigate(R.id.go_to_help)
        }

    }

    fun checkWritePermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_WRITE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        }
    }
}
