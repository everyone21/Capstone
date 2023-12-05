package com.example.capstone.LocalShops

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.List.LocalShopArray
import com.example.capstone.LocalshopAdapter
import com.example.capstone.R
import com.example.capstone.bottomMenu.PostShop
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class FoodnBev : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var shoplist : ArrayList<LocalShopArray>
    private lateinit var shopAdapter : LocalshopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodn_bev)

            recyclerView = findViewById(R.id.fnb)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            shoplist = arrayListOf()
            shopAdapter = LocalshopAdapter(shoplist)
            recyclerView.adapter = shopAdapter

            db = FirebaseFirestore.getInstance()
            db.collection("Shops").whereEqualTo("TypeOfBusiness", "Food and Beverages")
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                        if (error != null){

                            Log.e("Firestore Error", error.message.toString())
                            return
                        }

                        for (dc: DocumentChange in value?.documentChanges!!){
                            if (dc.type == DocumentChange.Type.ADDED){
                                shoplist.add(dc.document.toObject(LocalShopArray::class.java))
                            }
                        }
                        shopAdapter.notifyDataSetChanged()
                    }

                })


        }


}