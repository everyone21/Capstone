package com.example.capstone.bottomMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.capstone.R

class LocalShop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_shop)



        val postShop = findViewById<Button>(R.id.postShop)
        postShop.setOnClickListener {
            val intent = Intent(this, PostShop::class.java)
            startActivity(intent)
        }
    }
}