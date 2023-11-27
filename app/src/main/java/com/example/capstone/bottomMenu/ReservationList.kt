package com.example.capstone.bottomMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.capstone.R

class ReservationList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)

        val reserve = findViewById<Button>(R.id.AppointBtn)
        reserve.setOnClickListener {
            val intent = Intent(this, Reservation::class.java)
            startActivity(intent)
        }
    }
}