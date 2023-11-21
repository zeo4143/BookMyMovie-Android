package com.example.bookmymovie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.IOException

class SignIn : AppCompatActivity() {
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var registerHere:TextView
    private lateinit var signIn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

        userName = findViewById(R.id.signIn_userName)
        password = findViewById(R.id.signIn_password)
        registerHere = findViewById(R.id.RegisterHere)
        signIn = findViewById(R.id.signIn)

        registerHere.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }

        signIn.setOnClickListener {
            if (redirect(userName.text.toString(), password.text.toString())) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", userName.text.toString())
                startActivity(intent)
                finish()
            }
        }


    }

    private fun redirect(userName:String, password:String):Boolean {
        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        } else {
            if (isValid(userName, password)) {
                val sharedPreferences = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("userName", userName)
                editor.putString("password", password)
                editor.apply()

                return true

            } else {
                Toast.makeText(this, "Invalid ID or password", Toast.LENGTH_SHORT).show()
            }
        }

        return false
    }

    private fun isValid(userName: String, password: String): Boolean {
        try {
            val fin = openFileInput("userDetails.txt")
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            fin.close()

            val content = String(bytes)
            val lines = content.split("\n")

            for (line in lines) {
                val parts = line.split("@")
                if (parts.size >= 2) {
                    val storedUserName = parts[1]
                    val storedPassword = parts[2].trim()

                    if (userName == storedUserName && password == storedPassword) {
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        return true
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return false
    }
}