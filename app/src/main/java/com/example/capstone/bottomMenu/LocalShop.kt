package com.example.capstone.bottomMenu

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.Events
import com.example.capstone.LocalShopArray
import com.example.capstone.R
import com.example.capstone.LocalshopAdapter
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class LocalShop : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var shopsArray: ArrayList<LocalShopArray>
//    private var shopAdapter = LocalshopAdapter(shopsArray)
    private lateinit var shoplist : ArrayList<LocalShopArray>
    private lateinit var shopAdapter : LocalshopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_shop)

//        shopListener()
        val postShop = findViewById<Button>(R.id.postShop)
        postShop.setOnClickListener {
            val intent = Intent(this, PostShop::class.java)
            startActivity(intent)
        }

//        val recyclerView = findViewById<RecyclerView>(R.id.shops)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//
//        recyclerView.adapter = shopAdapter

    }




//    @SuppressLint("NotifyDataSetChanged")
//    private fun fetchData() {
//        db = FirebaseFirestore.getInstance()
//        db.collection("EventsAnnouncement").orderBy("eventDate", Query.Direction.ASCENDING)
//            .addSnapshotListener(object : EventListener<QuerySnapshot>{
//                @SuppressLint("NotifyDataSetChanged")
//                override fun onEvent(
//                    value: QuerySnapshot?,
//                    error: FirebaseFirestoreException?
//                ) {
//                    if (error != null){
//
//                        Log.e("Firestore Error", error.message.toString())
//                        return
//                    }
//
//                    for (dc:DocumentChange in value?.documentChanges!!){
//                        if (dc.type == DocumentChange.Type.ADDED){
//                            eventsArray.add(dc.document.toObject(Events::class.java))
//
//                        }
//                    }
//
//                    calendarAdapter.notifyDataSetChanged()
//                }
//
//
//            })
//    }


}