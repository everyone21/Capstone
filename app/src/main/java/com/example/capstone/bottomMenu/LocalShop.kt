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
import com.google.firebase.firestore.toObject

class LocalShop : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var shoplist : ArrayList<LocalShopArray>
    private lateinit var shopAdapter : LocalshopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_shop)

        val postShop = findViewById<Button>(R.id.postShop)
        postShop.setOnClickListener {
            val intent = Intent(this, PostShop::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.shopsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        shoplist = ArrayList()
        shopAdapter = LocalshopAdapter(shoplist)
        recyclerView.adapter = shopAdapter

        fetchData()


    }




    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData() {
        db = FirebaseFirestore.getInstance()
        db.collection("Shops")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null){

                        Log.e("Firestore Error", error.message.toString())
                        return
                    }

                    for (dc:DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED){
                            shoplist.add(dc.document.toObject(LocalShopArray::class.java))
                        }

                    }
                    shopAdapter.notifyDataSetChanged()
                }

            })

    }


}