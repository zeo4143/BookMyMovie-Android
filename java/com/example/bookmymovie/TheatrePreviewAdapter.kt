package com.example.bookmymovie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmymovie.models.ShowsByDate
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class TheatrePreviewAdapter(private var showDetails: ShowsByDate ) : RecyclerView.Adapter<TheatrePreviewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val theatreName : TextView = itemView.findViewById(R.id.theatre_show_timings_theatre_name)
        val childRecyclerView : RecyclerView = itemView.findViewById(R.id.theatre_show_timings_child_recyclerView)
        val imageView : ImageView = itemView.findViewById(R.id.theatre_show_timings_theatre_distance_icon)
        val distance: TextView = itemView.findViewById(R.id.theatre_show_timings_theatre_distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.theatre_show_timings, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  showDetails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var showsByTheatres = showDetails[position]
        Log.i("showsByTheatres", "onBindViewHolder: $showsByTheatres")

        holder.theatreName.text = showsByTheatres.theatreDetails.name

        holder.imageView.setOnClickListener {

        }

        //adding ChildAdapter
        val childAdapter : ShowsPreviewAdapter = ShowsPreviewAdapter(showsByTheatres.timings, showsByTheatres.theatreDetails)
        holder.childRecyclerView.adapter = childAdapter

    }


}