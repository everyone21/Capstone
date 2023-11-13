package com.example.capstone

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class calendarEditAdapter(private val eventsList2: ArrayList<Events>) : RecyclerView.Adapter<calendarEditAdapter.MyViewHolder2>() {

//    private lateinit var mListener : onItemClickListener
//
//
//    interface onItemClickListener{
//
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(listener2: onItemClickListener){
//
//        mListener = listener2
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.calendar_edit_view, parent, false)
        return MyViewHolder2(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val events: Events = eventsList2[position]
        holder.Title.text = events.eventTitle
        holder.Desc.text = events.eventDescription
        holder.Date.text = events.eventDate
    }

    override fun getItemCount(): Int = eventsList2.size


    class MyViewHolder2(itemView : View) : RecyclerView.ViewHolder(itemView){
        val Title: TextView = itemView.findViewById(R.id.titleTextView)
        val Desc: TextView = itemView.findViewById(R.id.descriptionTextView)
        val Date: TextView = itemView.findViewById(R.id.dateTextView)

//        init {
//            itemView.setOnClickListener {
//                listener2.onItemClick(adapterPosition)
//            }
//        }
    }


}