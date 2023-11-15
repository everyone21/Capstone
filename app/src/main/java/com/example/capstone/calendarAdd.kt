package com.example.capstone

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class calendarAdd : AppCompatActivity() {

    private lateinit var df: DocumentReference
    private lateinit var fStore: FirebaseFirestore

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_add)

        fStore = FirebaseFirestore.getInstance()

        val add: Button = findViewById(R.id.add)


        add.setOnClickListener {
            events()
        }
    }
    private fun events(){
        val title: EditText = findViewById(R.id.eventTitle)
        val desc: EditText = findViewById(R.id.eventDescription)

        val eventTitle = title.text.toString()
        val description = desc.text.toString()

        val datePicker: DatePicker = findViewById(R.id.datePicker)
        
        val day = datePicker.dayOfMonth.toString()
        val month = datePicker.month.toString()
        val year = datePicker.year.toString()
        val date = "$month/$day/$year"

        df = fStore.collection("EventsAnnouncement").document()
        val events = hashMapOf(
            "eventTitle" to eventTitle,
            "eventPlace" to description,
            "eventDate" to date
        )
        df.set(events)
        Toast.makeText(this, "uploaded", Toast.LENGTH_SHORT).show()


    }
}