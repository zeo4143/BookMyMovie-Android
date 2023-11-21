package com.example.bookmymovie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmymovie.helpers.DatabaseManager
import com.example.bookmymovie.models.BookingHistory

class MyBookings : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyBookingsAdapter
    private lateinit var initText: TextView


    override fun onCreateView( inflater: LayoutInflater,   container: ViewGroup?,  savedInstanceState: Bundle? ): View? {
        val view =  inflater.inflate(R.layout.fragment_my_bookings, container, false)

        //view Binders
        recyclerView = view.findViewById(R.id.recycler_view_my_bookings)
        initText = view.findViewById(R.id.myBooking_new_user_text)


        //database access
        val db = DatabaseManager.getDatabase(requireContext())
        val bookingHistory: LiveData<List<BookingHistory>> = db.bookingHistoryDao().getHistory()

        bookingHistory.observe(viewLifecycleOwner) { bookingHistoryList:List<BookingHistory> ->
            // The data has changed; you can access and use the updated list
            if (bookingHistoryList.isNotEmpty()) {
                // You can use allMoviesList here
                Log.i("RoomData", "onCreateView: $bookingHistoryList")
                adapter = MyBookingsAdapter(bookingHistoryList)
                recyclerView.adapter = adapter

            } else {
                // The LiveData doesn't have data yet or it's null
                //add textView to show that your are new User
                initText.text =
                    "uh oh! looks like you are new user, please bookings to see your bookings here"
            }
        }



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        DatabaseManager.closeDatabase()
    }
}