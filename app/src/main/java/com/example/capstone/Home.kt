package com.example.capstone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Home : Fragment() {
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var reportAdapter: ReportAdapter
    private lateinit var reportsArray: ArrayList<Report>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = FirebaseFirestore.getInstance()
        val reportsCollection = db.collection("reports")
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.postContainer)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        reportsArray = arrayListOf()
        reportAdapter = ReportAdapter(reportsArray)
//        val reportAdapter = ReportAdapter { report ->
//            val intent = Intent(requireContext(), ReportsDetailedActivity::class.java)
//            intent.putExtra("title", report.title)
//            intent.putExtra("date", report.date)
//            intent.putExtra("description", report.description)
//            intent.putExtra("mediaURL", report.mediaURL)
//            startActivity(intent)
//        }
        recyclerView.adapter = reportAdapter
//        ReportChangeListener()
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
                        val reports = mutableListOf<Report>()
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
                            reports.add(report)
                        }
                        reportAdapter.submitList(reports)
                    }
                }
            }
        return view

    }

//    private fun ReportChangeListener(){
//        db = FirebaseFirestore.getInstance()
//        val reportsCollection = db.collection("reports")
//
//        reportsCollection
//            .whereEqualTo("status", "Accepted")
//            .orderBy("timestamp", Query.Direction.DESCENDING)
//            .addSnapshotListener(object : EventListener<QuerySnapshot>{
//                @SuppressLint("NotifyDataSetChanged")
//                override fun onEvent(
//                    value: QuerySnapshot?,
//                    error: FirebaseFirestoreException?) {
//                    if (error != null){
//
//                        Log.e("Firestore Error", error.message.toString())
//                        return
//                    }
//                    for (dc: DocumentChange in value?.documentChanges!!){
//                        if (dc.type == DocumentChange.Type.ADDED){
//                            reportsArray.add(dc.document.toObject(Report::class.java))
//
//                        }
//
//                    }
//                    reportAdapter.notifyDataSetChanged()
//                }
//
//            })
//    }

    private fun formatDate(date: Date?): String {
        date?.let {
            val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            return sdf.format(date)
        }
        return ""
    }
}