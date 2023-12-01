package com.example.capstone.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.List.Report
import com.example.capstone.databinding.ReportItemBinding

class ReportAdapter(private val reportsList: java.util.ArrayList<Report>) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>()
 {

//    var onItemClick : ((Report) -> Unit)? = null
    class ReportViewHolder(val binding: ReportItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(ReportItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    }

     override fun getItemCount(): Int {
         return reportsList.size
     }

     override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reportsList[position]

         holder.apply {
             binding.apply {
                 titleTextView.text = report.title
                 descriptionTextView.text = report.description
                 dateTextView.text = report.date
//                 Picasso.get().load(report.mediaURL).into(mediaImageView)
                 if (report.mediaURL != null) {
                    mediaImageView.visibility = View.VISIBLE
                    Glide.with(itemView)
                        .load(report.mediaURL)
                        .placeholder(R.drawable.baseline_image_24) // Placeholder image
                        .error(R.drawable.baseline_image_24) // Error image (if loading fails)
                        .into(mediaImageView)
                } else {
                    mediaImageView.visibility = View.GONE
                }
             }


             }
         }
     }




//        holder.titleTextView.text = reportsList[position].title
//        holder.descriptionTextView.text = reportsList[position].description
//        holder.dateTextView.text = reportsList[position].date
//        holder.bind(reportsList[position])





//    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
//        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
//        private val mediaImageView: ImageView = itemView.findViewById(R.id.mediaImageView)
//        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
//        private val readMoreTextView: TextView = itemView.findViewById(R.id.readMoreTextView)
//
//
//        fun bind(report: Report) {
//            titleTextView.text = report.title
//            descriptionTextView.text = report.description
//            dateTextView.text = report.date
//
//            // Load and display media using Glide (or another image loading library)
//            if (report.mediaURL != null) {
//                mediaImageView.visibility = View.VISIBLE
//                Glide.with(itemView)
//                    .load(report.mediaURL)
//                    .placeholder(R.drawable.baseline_image_24) // Placeholder image
//                    .error(R.drawable.baseline_image_24) // Error image (if loading fails)
//                    .into(mediaImageView)
//            } else if (report.mediaURL == null) {
//                mediaImageView.visibility = View.GONE
//            }
//        }
//    }



//    class ReportDiffCallback : DiffUtil.ItemCallback<Report>() {
//        override fun areItemsTheSame(oldItem: Report, newItem: Report): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Report, newItem: Report): Boolean {
//            return oldItem == newItem
//        }
//    }
//}