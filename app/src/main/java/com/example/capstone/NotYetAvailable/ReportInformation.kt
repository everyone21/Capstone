package com.example.capstone.NotYetAvailable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.capstone.R

class ReportInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_information)

        val title: TextView = findViewById(R.id.reporttitle)
        val description: TextView = findViewById(R.id.reportdesc)
        val date: TextView = findViewById(R.id.date)
        val image: ImageView = findViewById(R.id.mediaImageView)

        val bundle: Bundle? = intent.extras
        val titl = bundle?.getString("title")

    }
}