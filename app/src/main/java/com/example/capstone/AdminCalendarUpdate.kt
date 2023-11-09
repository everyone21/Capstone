package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AdminCalendarUpdate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_calendar_update)

        val calendaradd = findViewById<Button>(R.id.add)
        val calendaredit = findViewById<Button>(R.id.edit)

        calendaradd.setOnClickListener {
            val add = Intent(this, calendarAdd::class.java)
            startActivity(add)
        }

        calendaredit.setOnClickListener {
            val edit = Intent(this, calendarEdit::class.java)
            startActivity(edit)
        }

        }
}