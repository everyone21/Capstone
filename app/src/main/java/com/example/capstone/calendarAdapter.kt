package com.example.capstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class calendarAdapter(private val eventsList: ArrayList<Event>) : RecyclerView.Adapter<calendarAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): calendarAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.anouncement_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: calendarAdapter.MyViewHolder, position: Int) {
        val events: Event = eventsList[position]
        holder.bind(events)
    }

    override fun getItemCount(): Int = eventsList.size


    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val Title: TextView = itemView.findViewById(R.id.titleTextView)
        val Desc: TextView = itemView.findViewById(R.id.descriptionTextView)
        val Date: TextView = itemView.findViewById(R.id.dateTextView)

        fun bind(event: Event) {
            Title.text = event.eventTitle
            Desc.text = event.eventDescription
            Date.text = event.eventDate

        }
    }
    data class Event(
        val eventTitle: String,
        val eventDescription: String,
        val eventDate: String
    )
}