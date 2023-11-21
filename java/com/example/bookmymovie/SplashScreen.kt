package com.example.bookmymovie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide

class SplashScreen : AppCompatActivity() {

    private lateinit var gif:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        gif = findViewById(R.id.splashScreen_Image)
        Glide.with(this).load(R.raw.splash_screen).into(gif)


        val sharedPref = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = if(isLoggedIn) {
                    val userName = sharedPref.getString("userName", "")
                    val name = sharedPref.getString("name", "")

                    Intent(this, MainActivity::class.java).apply {
                        putExtra("userName", userName)
                        putExtra("name", name)
                    }
                }
                else {
                    Intent(this, SignIn::class.java)
                }

                startActivity(intent)
                finish()
            },7000)
    }

}