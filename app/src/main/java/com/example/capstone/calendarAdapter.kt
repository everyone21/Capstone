package com.example.capstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class calendarAdapter(private val eventsList: ArrayList<Events>) : RecyclerView.Adapter<calendarAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.anouncement_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val events: Events = eventsList[position]
        holder.Title.text = events.eventTitle
        holder.Desc.text = events.eventDescription
        holder.Date.text = events.eventDate
    }

    override fun getItemCount(): Int = eventsList.size


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val Title: TextView = itemView.findViewById(R.id.titleTextView)
        val Desc: TextView = itemView.findViewById(R.id.descriptionTextView)
        val Date: TextView = itemView.findViewById(R.id.dateTextView)

    }
}