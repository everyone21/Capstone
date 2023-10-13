package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        auth = Firebase.auth

        val back: Button = findViewById(R.id.backbttn)
        val create: Button = findViewById(R.id.submit)

        back.setOnClickListener{

            val login = Intent(this, LoginPage::class.java)
            startActivity(login)

        }
        // User SignUp function
        create.setOnClickListener {
            userSignUp()
        }
    }

    private fun userSignUp() {

        val Uname: EditText = findViewById(R.id.username)
        val email: EditText = findViewById(R.id.email)
        val phone_num: EditText = findViewById(R.id.cellnum)
        val pass: EditText = findViewById(R.id.password)
        val confirmPass: EditText = findViewById(R.id.confirm_pass)


        val Email = email.text.toString()
        val Password = pass.text.toString()
        val Confirm = confirmPass.text.toString()

        if (email.text.isEmpty() or pass.text.isEmpty()){

            Toast.makeText(this, "Please Fill All the Fields", Toast.LENGTH_SHORT).show()
            return
        }
        else if(Password == Confirm) {

            auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, To MainActivity
                        val main = Intent(this, MainActivity::class.java)
                        startActivity(main)

                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()


                    } else {
                        Toast.makeText(
                            baseContext, "Authentication failed.", Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "An Error Occured ${it.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }

        else{
            Toast.makeText(this, "Password and Confirm-Password Does not match", Toast.LENGTH_SHORT).show()

        }

    }



}