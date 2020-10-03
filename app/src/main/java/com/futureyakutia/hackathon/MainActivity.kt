package com.futureyakutia.hackathon

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import com.futureyakutia.hackathon.appeal.Appeal
import com.futureyakutia.hackathon.appeal.generator.AppealGenerator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_WRITE = 10
    }

    private val appeal = Appeal().apply {
        with(animalAbuse) {
            userName = "Иванов Иван Иванович"
            caseDescriptionByUser = "Возвращаясь домой я наблюдал ужасную картину, то как гр. Петров измывался над бедной собакой. Сначала он бил ее палкой, затем тушил об нее окурки и никто ничего ему не сделал"
            childrenWitnesses = true
            commitmentDate = "02.10.2020"
            commitmentTime = "18:56"
            commitmentPlace = "город Якутск, двор улицы пр. Ленина 1"
            suspect = "Петров Петр Петрович"
        }
    }
    private val appealGenerator = AppealGenerator(appeal, this)


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
            appealGenerator.getDocx()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE);
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
            appealGenerator.getDocx()
        }
    }
}
