package com.example.capstone.bottomMenu

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.capstone.R
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date
import java.util.Locale

class adminReservationView : AppCompatActivity() {

    private lateinit var currentDate: Date
    private lateinit var dateDisplay: TextView
    private lateinit var appointmentsRef: CollectionReference

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_reservation_view)

        dateDisplay = findViewById(R.id.dateDisplay)

        appointmentsRef = FirebaseFirestore.getInstance().collection("Appointments")

        val currentDateDocument = appointmentsRef.document("currentDateDocument")
        currentDateDocument.get().addOnSuccessListener { documentSnapshot ->
            currentDate = if (documentSnapshot.exists()) {
                documentSnapshot.getDate("currentDate") ?: Date()
            } else {
                Date()
            }
            displayDate(currentDate)
            updateAppointmentLimits()
        }

        val previousBtn = findViewById<ImageView>(R.id.previousBtn)
        val nextBtn = findViewById<ImageView>(R.id.nextBtn)

        previousBtn.setOnClickListener {
            currentDate = getPreviousDate(currentDate)
            displayDate(currentDate)
            updateAppointmentLimits()
        }

        nextBtn.setOnClickListener {
            currentDate = getNextDate(currentDate)
            displayDate(currentDate)
            updateAppointmentLimits()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getPreviousDate(currentDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DATE, -1)
        return calendar.time
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getNextDate(currentDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DATE, 1)
        return calendar.time
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun displayDate(date: Date) {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        dateDisplay.text = dateFormat.format(date)
    }

    //edit only below this line
    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateAppointmentLimits() {
        // Clear the previous counts before updating
        clearPreviousCounts()
        // Get the appointments for the current date
        appointmentsRef.whereEqualTo("month", getMonth(currentDate))
            .whereEqualTo("day", getDay(currentDate))
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val time = document.getString("time")
                    val count = document.getLong("count") ?: 0

                    Log.d("AppointmentUpdate", "Time: $time, Count: $count")

                    when (time) {
                        "9:00 AM" -> updateLimitText(R.id.limit1, count)
                        "10:00 AM" -> updateLimitText(R.id.limit2, count)
                        "11:00 AM" -> updateLimitText(R.id.limit3, count)
                        "1:00 PM" -> updateLimitText(R.id.limit4, count)
                        "2:00 PM" -> updateLimitText(R.id.limit5, count)
                        "3:00 PM" -> updateLimitText(R.id.limit6, count)
                        "4:00 PM" -> updateLimitText(R.id.limit7, count)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("AppointmentUpdate", "Error getting documents: ", exception)
            }
    }

    private fun clearPreviousCounts() {
        // Clear the previous counts on UI
        updateLimitText(R.id.limit1, 0)
        updateLimitText(R.id.limit2, 0)
        updateLimitText(R.id.limit3, 0)
        updateLimitText(R.id.limit4, 0)
        updateLimitText(R.id.limit5, 0)
        updateLimitText(R.id.limit6, 0)
        updateLimitText(R.id.limit7, 0)
    }

    private fun updateLimitText(limitId: Int, count: Long) {
        val limitTextView = findViewById<TextView>(limitId)
        limitTextView.text = "$count/20"

        if (count > 20) {
            // Delete the appointment if count exceeds 20
            deleteAppointment()
        }
    }

    private fun deleteAppointment() {
        // Implement the logic to delete the appointment
        // You need to get the document reference and call delete() method
        // Example: appointmentsRef.document(documentId).delete()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getMonth(date: Date): String {
        val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        return dateFormat.format(date)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDay(date: Date): String {
        val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
        return dateFormat.format(date)
    }
}