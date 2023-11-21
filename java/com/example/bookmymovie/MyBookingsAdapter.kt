package com.example.bookmymovie

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmymovie.models.BookingHistory

class MyBookingsAdapter(private var bookings: List<BookingHistory>) : RecyclerView.Adapter<MyBookingsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val movieName: TextView = itemView.findViewById(R.id.moive_ticket_summary_movieName)
        private val movieType: TextView = itemView.findViewById(R.id.movie_ticket_summary_movieType)
        val movieLanguage:TextView = itemView.findViewById(R.id.moive_ticket_summary_movieLanguage)
        val movieView: TextView = itemView.findViewById(R.id.moive_ticket_summary_movieView)
        val theatreAddress: TextView = itemView.findViewById(R.id.moive_ticket_summary_theatrAddress)
        val showDateAndTime: TextView = itemView.findViewById(R.id.moive_ticket_summary_shoeDataAndTime)
        val audiAndSeatNoS: TextView = itemView.findViewById(R.id.moive_ticket_summary_seatNoS)
        val noOfTickets: TextView = itemView.findViewById(R.id.moive_ticket_summary_noOfTickets)

        fun bind(booking: BookingHistory) {
            Log.i("bookingDetails", "bind: $booking")
            movieName.text = booking.movieName
            movieType.text = booking.movieType
            // Bind more views if needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_ticket_summary_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return bookings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookingHistory = bookings[position]
        Log.i("bookHistory", "onBindViewHolder: $bookingHistory")
        //Add all view holders
       holder.bind(bookingHistory)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,TicketBookedSuccessful::class.java)
//
//            // Start the main activity
            holder.itemView.context.startActivity(intent)
        }
        
    }
}