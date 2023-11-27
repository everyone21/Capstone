package com.example.capstone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class calendarEditActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_edit2)

        val back: ImageButton = findViewById(R.id.back)

        back.setOnClickListener{
            val intent = Intent(this, calendarEdit::class.java)
            startActivity(intent)
            finish()
        }

        val title: TextView = findViewById(R.id.what)
        val date: TextView = findViewById(R.id.when_)
        val desc: TextView = findViewById(R.id.where_1)
        val time: TextView = findViewById(R.id.time)

        val bundle : Bundle?= intent.extras
        val titl = bundle?.getString("title")
        val dat = bundle?.getString("date")
        val descr = bundle?.getString(("place"))
        val tim = bundle?.getString(("time"))


        title.text = titl
        date.text = dat
        desc.text = descr
        time.text = tim
        }
}