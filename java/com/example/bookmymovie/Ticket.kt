package com.example.bookmymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bookmymovie.models.TheatreDetails
import com.example.bookmymovie.models.Timing

@Suppress("DEPRECATION")
class Ticket : AppCompatActivity() {

    private lateinit var parentConstraintLayout: ConstraintLayout
    private lateinit var gridView: GridView
    private lateinit var textViewHeader:TextView
    private lateinit var textViewContent:TextView
    private lateinit var theatreName:TextView
    private lateinit var theatreAddress:TextView
    private lateinit var childLayoutButton: Button
    private lateinit var seatAdapter: SeatAdapter

    private val seatList = mutableListOf<Seats>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)


        parentConstraintLayout = findViewById(R.id.tickets_layout_display)
        theatreName = findViewById(R.id.ticket_theatre_name)
        theatreAddress = findViewById(R.id.ticket_theatre_address)
        gridView = findViewById(R.id.cinema_layout_gridView)

        //getExtra
        val showDetails = intent.getParcelableExtra<Timing>("showDetails")
        val theatreDetails = intent.getParcelableExtra<TheatreDetails>("theatreDetails")

        //adding theatre Name & Details
        if(theatreDetails != null) {
            theatreName.text = theatreDetails.name
            theatreAddress.text = theatreDetails.address
        }

        if(showDetails != null) {
            Log.i("ticketDetails", "onCreate: $showDetails")
        }

        //Inflating bottomLayout to parentLayout
        val childConstraintLayout = layoutInflater.inflate(R.layout.bottom_layout, parentConstraintLayout,false)

        textViewHeader = childConstraintLayout.findViewById(R.id.bottom_layout_heading)
        textViewContent = childConstraintLayout.findViewById(R.id.bottom_layout_content)
        childLayoutButton = childConstraintLayout.findViewById(R.id.bottom_layout_button)

        textViewHeader.text = "Total"
        textViewContent.text = "Seats"
        childLayoutButton.text = "Continue"

        //Add the childLayout to the parentLayout
        parentConstraintLayout.addView(childConstraintLayout)


        // Populate seatList with example seats
        for (row in 1..5) {
            for (column in 1..5) {
                seatList.add(Seats(row, column))
            }
        }

        seatAdapter = SeatAdapter(this, seatList)
        gridView.adapter = seatAdapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedSeat = seatList[position]
            selectedSeat.isSelected = !selectedSeat.isSelected
//            val selectedSeats = seatList.filter { it.isSelected }
//            textViewContent.text  = "${selectedSeats.size} Seats"
            seatAdapter.notifyDataSetChanged()
        }

        childLayoutButton.setOnClickListener {
            val selectedSeats = seatList.filter { it.isSelected }
            // Handle booking logic here
            // For example, display a message with the selected seats
            val message = if (selectedSeats.isNotEmpty()) {
                "Selected Seats: ${selectedSeats.joinToString { "Row ${it.row}, Column ${it.column}" }}"
            } else {
                "No seats selected"
            }
            // Display a toast message or perform further booking actions
            // For simplicity, a toast message is used here
            showToast(message)
            val intent = Intent(this, BookingSummary::class.java).apply {
                putExtra("BookingSummary", message)
            }
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}