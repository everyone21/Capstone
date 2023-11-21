package com.example.capstone.bottomMenu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.capstone.R
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class Reservation : AppCompatActivity() {

    private lateinit var spinner : Spinner
    private lateinit var purokSpinner: Spinner
    private lateinit var monthSpinner: Spinner
    private lateinit var daySpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var firstName : EditText
    private lateinit var lastName : EditText
    private lateinit var submit : Button
    private lateinit var df: DocumentReference
    private lateinit var fStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        fStore = FirebaseFirestore.getInstance()

        spinner = findViewById(R.id.purpose_spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.purpose, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        purokSpinner = findViewById(R.id.purok_spinner)
        val purokAdapter = ArrayAdapter.createFromResource(this, R.array.Puroks, android.R.layout.simple_spinner_item)
        purokAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        purokSpinner.adapter = purokAdapter


        monthSpinner = findViewById(R.id.monthSpinner)
        val monthAdapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = monthAdapter


        daySpinner = findViewById(R.id.daySpinner)
        val dayAdapter = ArrayAdapter.createFromResource(this, R.array.Day, android.R.layout.simple_spinner_item)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySpinner.adapter = dayAdapter


        timeSpinner = findViewById(R.id.timeSpinner)
        val timeAdapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeAdapter


        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)





        //Not Working properly

        submit = findViewById(R.id.submit)

        submit.setOnClickListener {

            val fname = firstName.text.toString()
            val lname = lastName.text.toString()
            val purp = spinner.onItemSelectedListener.toString()
            val purok = purokSpinner.onItemSelectedListener.toString()
            val month = monthSpinner.onItemSelectedListener.toString()
            val day = daySpinner.onItemSelectedListener.toString()
            val time = timeSpinner.onItemSelectedListener.toString()

            df = fStore.collection("Appointments").document()
            val appointment = hashMapOf(
                "first_name" to fname,
                "last_name" to lname,
                "purpose" to purp,
                "purok" to purok,
                "month" to month,
                "day" to day,
                "time" to time
            )
            df.set(appointment)
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()

        }

//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val selectedItem = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(this@Reservation, "You Have Appointed $selectedItem", Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//
//        }

    }
}