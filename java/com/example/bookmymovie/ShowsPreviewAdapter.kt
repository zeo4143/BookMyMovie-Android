package com.example.bookmymovie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmymovie.models.TheatreDetails
import com.example.bookmymovie.models.Timing
import org.w3c.dom.Text

class ShowsPreviewAdapter(private var showTimings : List<Timing>, private val theatreDetails: TheatreDetails) : RecyclerView.Adapter<ShowsPreviewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val time : TextView = itemView.findViewById(R.id.show_timings_time)
//        val movie : TextView = add afterwards
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_timings,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return showTimings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var timings = showTimings[position]

        holder.time.text = timings.time

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Ticket::class.java)
            intent.putExtra("showDetails", timings)
            intent.putExtra("theatreDetails", theatreDetails)
            holder.itemView.context.startActivity(intent)
        }
    }
}