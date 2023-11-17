package com.example.capstone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.capstone.bottomMenu.LocalShop
import com.example.capstone.bottomMenu.MessageActivity
import com.example.capstone.bottomMenu.Reservation

class bottomMenuBurger : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bottom_menu_burger, container, false)

        val shop = view.findViewById<CardView>(R.id.localShop)
        val reserve = view.findViewById<CardView>(R.id.reservation)
        val message = view.findViewById<CardView>(R.id.message)

        message.setOnClickListener {
            val intent = Intent(activity, MessageActivity::class.java)
            startActivity(intent)
        }

        reserve.setOnClickListener {
            val intent2 = Intent(activity, Reservation::class.java)
            startActivity(intent2)
        }

        shop.setOnClickListener {
            val intent3 = Intent(activity, LocalShop::class.java)
            startActivity(intent3)
        }


        return view
    }


}