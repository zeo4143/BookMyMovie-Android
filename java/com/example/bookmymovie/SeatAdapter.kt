package com.example.bookmymovie


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SeatAdapter(private val context: Context, private val seatList: List<Seats>) : BaseAdapter() {

    override fun getCount(): Int = seatList.size

    override fun getItem(position: Int): Any = seatList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val seat = getItem(position) as Seats
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.ticket_grid_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.seatTextView.text = "${seat.row},${seat.column}"

        // Update background based on seat selection
        if (seat.isSelected) {
            viewHolder.seatTextView.setBackgroundResource(R.drawable.selected_seat_background)
        } else {
            viewHolder.seatTextView.setBackgroundResource(R.drawable.available_seat_background)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val seatTextView: TextView = view.findViewById(R.id.seatTextView)
    }
}
