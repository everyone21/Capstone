package com.example.capstone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class AdminCalendarView : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_admin_calendar_view, container, false)

        val calendaradd:Button = view.findViewById(R.id.add)
        val calendaredit:Button = view.findViewById(R.id.edit)

        calendaradd.setOnClickListener {
            val add = Intent(requireContext(), calendarAdd::class.java)
            startActivity(add)
        }

        calendaredit.setOnClickListener {
            val edit = Intent(requireContext(), calendarEdit::class.java)
            startActivity(edit)
        }

        return view
    }


}