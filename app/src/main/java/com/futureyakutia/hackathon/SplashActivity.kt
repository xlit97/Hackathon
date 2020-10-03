package com.futureyakutia.hackathon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private val coroutineContext = SupervisorJob()
    private val coroutineScope = CoroutineScope(coroutineContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
    }

    override fun onResume() {
        super.onResume()
        runBlocking {
            delay(3000)
        }
        startActivity(Intent(this, MainActivity::class.java))
    }
}