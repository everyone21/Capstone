package com.example.capstone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.Adapters.ReportAdapter
import com.example.capstone.List.Report
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Home : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reportAdapter: ReportAdapter
    private lateinit var reportsList: ArrayList<Report>

    @SuppressLint("NotifyDataSetChanged", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = FirebaseFirestore.getInstance()
        val reportsCollection = db.collection("reports")
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val add: FloatingActionButton = view.findViewById(R.id.addReport)

        add.setOnClickListener{
            val intent = Intent(context, UserReport::class.java)
            startActivity(intent)
        }

        recyclerView = view.findViewById(R.id.postContainer)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.setHasFixedSize(true)
        reportsList = arrayListOf()
        reportAdapter = ReportAdapter(reportsList)

        recyclerView.adapter = reportAdapter

        // Fetch reports from Firestore and update the adapter
        reportsCollection
            .whereEqualTo("status", "Accepted")
            .orderBy("timestamp", Query.Direction.DESCENDING) // Order by timestamp in descending order
            .addSnapshotListener{ snapshot, e ->
                if (e != null) {
                    // Handle the error
                }
                else {
                    snapshot?.let { nonNullSnapshot ->
                        for (document in nonNullSnapshot.documents) {
                            val id = document.id
                            val title = document.getString("title") ?: ""
                            val description = document.getString("description") ?: ""
                            val mediaURL = document.getString("mediaURL")
                            val timestamp = document.getDate("timestamp")
                            val formattedDate = formatDate(timestamp)
                            val report = Report(
                                id,
                                title,
                                description,
                                mediaURL,
                                formattedDate
                            )
                            reportsList.add(report)
                        }

                        reportAdapter.notifyDataSetChanged()
                    }
                }
            }
        return view

    }


    private fun formatDate(date: Date?): String {
        date?.let {
            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            return sdf.format(date)
        }
        return ""
    }

}