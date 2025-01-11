package com.example.authenticationapp2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.handleCoroutineException

class SplashActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            GotoMainActiviy()
        },3000L)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
//
//        // Mulai animasi
//        val splashProfilePicture = findViewById<ImageView>(R.id.splash_profile_picture)
//        val tvAppTitle = findViewById<TextView>(R.id.tv_app_title)
//        val tvName = findViewById<TextView>(R.id.tv_name)
//        val tvNim = findViewById<TextView>(R.id.tv_nim)

//        splashProfilePicture.startAnimation(fadeIn)
//        tvAppTitle.startAnimation(fadeIn)
//        tvName.startAnimation(fadeIn)
//        tvNim.startAnimation(fadeIn)

    }
    private fun GotoMainActiviy(){
        var it = Intent(this,MainActivity::class.java)
        startActivity(it)
    }
}