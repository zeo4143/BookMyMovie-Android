package com.example.bookmymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bookmymovie.helpers.DatabaseManager
import com.example.bookmymovie.models.BookingHistory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChoosePaymentMethod : AppCompatActivity() {

    private lateinit var parentLayout: ConstraintLayout
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_payment_method)

        parentLayout = findViewById(R.id.choose_payment_method_child)
        radioGroup = findViewById(R.id.radioGroup)

        val childLayout = layoutInflater.inflate(R.layout.bottom_layout, parentLayout,false)

        val childLayoutButton = childLayout.findViewById<Button>(R.id.bottom_layout_button)
        val childLayoutHeader = childLayout.findViewById<TextView>(R.id.bottom_layout_heading)
        val childLayoutContent = childLayout.findViewById<TextView>(R.id.bottom_layout_content)

        childLayoutButton.text = "Book Ticket"
        childLayoutHeader.text = ""
        childLayoutContent.text = ""

        parentLayout.addView(childLayout)

        val db = DatabaseManager.getDatabase(applicationContext)


        childLayoutButton.setOnClickListener {
            val selectPaymentMethod = radioGroup.checkedRadioButtonId

            if(selectPaymentMethod == -1) {
                Toast.makeText(this, "Select Payment method", Toast.LENGTH_SHORT).show()
            } else {
                val li = BookingHistory(movieName = "Jawan" , movieType = "U/A")
                db.bookingHistoryDao().insertBookedMovie(li)
                val intent = Intent(this, TicketBookedSuccessful::class.java)
                startActivity(intent)
            }

        }

    }



}