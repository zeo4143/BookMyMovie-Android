package com.example.bookmymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class BookingSummary : AppCompatActivity() {

    private lateinit var childConstraintLayout1: ConstraintLayout
    private lateinit var childConstraintLayout2: ConstraintLayout

    //cardView Constraints
    private lateinit var movieName:TextView
    private lateinit var movieType:TextView
    private lateinit var movieLanguage:TextView
    private lateinit var movieView:TextView
    private lateinit var theatreAddress:TextView
    private lateinit var showDateAndTime:TextView
    private lateinit var audiAndSeatNoS:TextView
    private lateinit var noOfTickets:TextView


    //bottomLayout Constraints
    private lateinit var textViewHeader: TextView
    private lateinit var textViewContent: TextView
    private lateinit var childButtonText: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_summary)
        supportActionBar?.hide()

        childConstraintLayout1 = findViewById(R.id.booking_summary_layout_child1)
        childConstraintLayout2 = findViewById(R.id.booking_summary_layout_child2)



        //Inflating movie_ticket_overview to parent
        val childCardView = layoutInflater.inflate(R.layout.movie_ticket_summary_card,childConstraintLayout1,false)

        movieName = childCardView.findViewById(R.id.moive_ticket_summary_movieName)
        movieType = childCardView.findViewById(R.id.movie_ticket_summary_movieType)
        movieLanguage = childCardView.findViewById(R.id.moive_ticket_summary_movieLanguage)
        movieView = childCardView.findViewById(R.id.moive_ticket_summary_movieView)
        theatreAddress = childCardView.findViewById(R.id.moive_ticket_summary_theatrAddress)
        showDateAndTime = childCardView.findViewById(R.id.moive_ticket_summary_shoeDataAndTime)
        audiAndSeatNoS = childCardView.findViewById(R.id.moive_ticket_summary_seatNoS)
        noOfTickets = childCardView.findViewById(R.id.moive_ticket_summary_noOfTickets)


//        val valueEachTicketprice=200
//        val valueNoOfTickets=5
//        val valuetotalTicketPrice=valueNoOfTickets*valueEachTicketprice
//
//        val valueBookingCharge=(10.0/100)*valuetotalTicketPrice
//        val valueserviceTax=(5.0/100)*valuetotalTicketPrice
//        val valueTotalPrice=(valuetotalTicketPrice+valueBookingCharge+valueserviceTax


        //add dynamic text to text views
        movieName.text = "Karthikeya-2"
        movieType.text = "U/A"
        movieLanguage.text = "Telugu"
        movieView.text = "2D"
        theatreAddress.text = "INOX, Jalandhar"
        showDateAndTime.text = "Sunday, 05, 5:30 PM"
        audiAndSeatNoS.text = "AUDI 05, Row 05, column 04"
        noOfTickets.text = "5"


        //Add to parent
        childConstraintLayout1.addView(childCardView)



        //Inflating bottomLayout to parentLayout
        val grandChildConstraintLayout = layoutInflater.inflate(R.layout.bottom_layout, childConstraintLayout2,false)

        textViewHeader = grandChildConstraintLayout.findViewById(R.id.bottom_layout_heading)
        textViewContent = grandChildConstraintLayout.findViewById(R.id.bottom_layout_content)
        childButtonText = grandChildConstraintLayout.findViewById(R.id.bottom_layout_button)

        textViewHeader.text = "view summary"
        textViewContent.text = "2120"
        childButtonText.text = "Proceed to Pay"

        //Add the childLayout2 to the parentLayout
        childConstraintLayout2.addView(grandChildConstraintLayout)

        childButtonText.setOnClickListener {
            val intent = Intent(this, ChoosePaymentMethod::class.java)
            startActivity(intent)
        }

    }
}