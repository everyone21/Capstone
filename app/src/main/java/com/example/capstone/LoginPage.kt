package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val loginbtn: Button = findViewById(R.id.LoginBtn)
        val Uname: EditText = findViewById(R.id.Username)
        val Pswrd: EditText = findViewById(R.id.Password)





    }
}