package com.example.capstone.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.capstone.R

class BarangayOfficials : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barangay_officials)
        val back = findViewById<Button>(R.id.back)

        back.setOnClickListener { finish() }
    }
}