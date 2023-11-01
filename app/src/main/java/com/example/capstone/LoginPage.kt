package com.example.capstone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    companion object {
        const val ADMIN_EMAIL = "admin@email.com"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val loginbtn: Button = findViewById(R.id.LoginBtn)
        val signup: TextView = findViewById(R.id.signup)

        signup.setOnClickListener {
            val signuppage = Intent(this, SignUpPage::class.java)
            startActivity(signuppage)
        }

        loginbtn.setOnClickListener {
            // User Login function
            Login()
        }
    }

    private fun Login(){

        val Uname: EditText = findViewById(R.id.Username)
        val Pswrd: EditText = findViewById(R.id.Password)

        if (Uname.text.isEmpty() or Pswrd.text.isEmpty()){

            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        val login_email = Uname.text.toString()
        val login_pass = Pswrd.text.toString()

        auth.signInWithEmailAndPassword(login_email, login_pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user?.email == ADMIN_EMAIL){
                        val intent = Intent(this, navigation::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show()
                    }else {
                        val intent = Intent(this, navigation::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show()
                        }


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Authentication Failed ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }


    }

}