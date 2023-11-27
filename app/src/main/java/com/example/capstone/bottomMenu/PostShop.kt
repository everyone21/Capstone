package com.example.capstone.bottomMenu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.capstone.R
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class PostShop : AppCompatActivity() {

    private lateinit var df: DocumentReference
    private lateinit var fStore: FirebaseFirestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_shop)


        fStore = FirebaseFirestore.getInstance()

        val shop = findViewById<EditText>(R.id.shopName)
        val shopD = findViewById<EditText>(R.id.shopDescription)
        val shopL = findViewById<EditText>(R.id.shopLocation)
        val post = findViewById<Button>(R.id.post)

        post.setOnClickListener {
            val shopName = shop.text.toString()
            val shopDescription = shopD.text.toString()
            val location = shopL.text.toString()

            df = fStore.collection("Shops").document()
            val shops = hashMapOf(
                "ShopName" to shopName,
                "ShopDescription" to shopDescription,
                "ShopLocation" to location
            )
            df.set(shops)
            Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show()

        }
    }
}