package com.example.bookmymovie

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TicketBookedSuccessful : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tciket_booked_successful)
        val goToHomepageButton = findViewById<Button>(R.id.goToHomepageButton)
        goToHomepageButton.setOnClickListener {
            // Define an Intent to navigate to the main activity (replace MainActivity::class.java with your actual main activity class)
            val intent = Intent(this, MainActivity::class.java)

            // Start the main activity
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}