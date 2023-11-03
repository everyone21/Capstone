package com.example.capstone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*


class Home : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reportAdapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = FirebaseFirestore.getInstance()
        val reportsCollection = db.collection("reports")
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.postContainer)
        recyclerView.layoutManager = LinearLayoutManager(context)
        reportAdapter = ReportAdapter()
        recyclerView.adapter = reportAdapter

        // Fetch reports from Firestore and update the adapter
        reportsCollection
            .whereEqualTo("status", "Accepted")
            .orderBy("timestamp", Query.Direction.DESCENDING) // Order by timestamp in descending order
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    // Handle the error
                } else {
                    snapshot?.let { nonNullSnapshot ->
                        val reports = mutableListOf<ReportAdapter.Report>()
                        for (document in nonNullSnapshot.documents) {
                            val id = document.id
                            val title = document.getString("title") ?: ""
                            val description = document.getString("description") ?: ""
                            val mediaURL = document.getString("mediaURL")
                            val timestamp = document.getDate("timestamp")
                            val formattedDate = formatDate(timestamp)
                            val report = ReportAdapter.Report(
                                id,
                                title,
                                description,
                                mediaURL,
                                formattedDate
                            )
                            reports.add(report)
                        }
                        reportAdapter.submitList(reports)
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