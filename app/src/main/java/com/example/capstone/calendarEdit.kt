package com.example.capstone

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class calendarEdit : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventsArray: ArrayList<Events>
    private lateinit var calendarAdapter: calendarEditAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_edit)
        recyclerView = findViewById(R.id.editView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        eventsArray = arrayListOf()
        calendarAdapter = calendarEditAdapter(eventsArray)
        recyclerView.adapter = calendarAdapter

        EventChangeListener()


    }
    private fun EventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("EventsAnnouncement").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
//            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null){

                    Log.e("Firestore Error", error.message.toString())
                    return
                }

                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        eventsArray.add(dc.document.toObject(Events::class.java))

                    }
                }
                var adapter = calendarEditAdapter(eventsArray)
                calendarAdapter.notifyDataSetChanged()
            }


        })

    }
}

//adapter.setOnItemClickListener(object : calendarEditAdapter.onItemClickListener{
//    override fun onItemClick(position: Int) {
//
//        Toast.makeText(this@calendarEdit, "You clicked this shiet no. $position", Toast.LENGTH_SHORT).show()
//
//    }
//})