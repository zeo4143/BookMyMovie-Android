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

class SignUp : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var userName:EditText
    private lateinit var password:EditText
    private lateinit var confirmPassword:EditText
    private lateinit var gotoSignIn:TextView
    private lateinit var signUp:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        name = findViewById(R.id.signUp_Name)
        userName = findViewById(R.id.signUp_userName)
        password = findViewById(R.id.signUp_password)
        confirmPassword = findViewById(R.id.signUp_confirmPassword)
        gotoSignIn = findViewById(R.id.gotoSignIn)
        signUp = findViewById(R.id.signUp)

        gotoSignIn.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }

        signUp.setOnClickListener {

            if(
                creteUser(
                    name.text.toString(),
                    userName.text.toString(),
                    password.text.toString(),
                    confirmPassword.text.toString()
                )
            ) {
                startActivity(Intent(this, SignIn::class.java))
                finish()
            }
        }
    }

    private fun creteUser(name: String, userName: String, password: String, confirmPassword: String): Boolean {
        if(checkUserDetails(name, userName, password, confirmPassword)) {
            try {

                val fout = openFileOutput("userDetails.txt", MODE_APPEND)
                val nameF = "$name@"
                val userNameF = "$userName@"
                val passwordF = "$password\n"

                fout.write(nameF.toByteArray())
                fout.write(userNameF.toByteArray())
                fout.write(passwordF.toByteArray())
                fout.close()

                val sharedPreferences = getSharedPreferences("Credentials", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("userName", userName)
                editor.putString("password", password)
                editor.apply()

                return true

            } catch (e : IOException) {
                e.printStackTrace()
            }
        }

        return false
    }



    private fun checkUserDetails(name: String,userName: String,password: String,confirmPassword: String): Boolean {

        if(
            name.isEmpty() ||
            userName.isEmpty() ||
            password.isEmpty() ||
            confirmPassword.isEmpty()
        ) {
            Toast.makeText(this, "All fields Required", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.length < 8) {
            Toast.makeText(this, "Password should be at least 8 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords MisMatched", Toast.LENGTH_SHORT).show()
            return false
        }
        Toast.makeText(this, "Registered Successfully!Sign In Please.", Toast.LENGTH_SHORT).show()
        return true
    }
}