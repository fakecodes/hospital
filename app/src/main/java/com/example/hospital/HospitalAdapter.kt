package com.example.hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HospitalAdapter(
    private val hospitals: List<Hospital>,
    private val onItemClick: (Hospital) -> Unit
) : RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital, parent, false)
        return HospitalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        holder.bind(hospitals[position], onItemClick)
    }

    override fun getItemCount() = hospitals.size

    class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.hospitalName)
        private val addressTextView: TextView = itemView.findViewById(R.id.hospitalAddress)
        private val imageView: ImageView = itemView.findViewById(R.id.hospitalImage)

        fun bind(hospital: Hospital, onItemClick: (Hospital) -> Unit) {
            nameTextView.text = hospital.name
            addressTextView.text = hospital.address
            Glide.with(itemView.context)
                .load("https://picsum.photos/200")
                .into(imageView)

            itemView.setOnClickListener { onItemClick(hospital) }
        }
    }
}
