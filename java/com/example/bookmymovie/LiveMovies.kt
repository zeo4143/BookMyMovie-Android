package com.example.bookmymovie


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmymovie.models.MovieData
import com.example.bookmymovie.services.MovieService.RetrofitObjectBuild.retrofitInstance
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LiveMovies : Fragment() {


    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: DisplayMoviesAdapter
    private lateinit var fab:FloatingActionButton

    override fun onCreateView( inflater: LayoutInflater,   container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        val view = inflater.inflate(R.layout.fragment_live_movies, container, false)

        //Fetch Location from shared preferences
        val sharedPreferences = requireActivity().getSharedPreferences("Credentials",Context.MODE_PRIVATE )
        val cityId = sharedPreferences.getString("cityId" , "Jalandhar").toString()

        if(cityId != "Jalandhar") {
            //fetch movies by city
            getAllMoviesByCity(cityId)
        } else {
           //fetch movie by city by asking permission to user  / choose city alert
        }

        //view binders
        recyclerView = view.findViewById(R.id.recyclerView)
        fab = view.findViewById(R.id.fab)

        fab.setOnClickListener {
            val intent = Intent(requireContext(), CityListView::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }

    private fun getAllMoviesByCity(city:String) {

        val getMoviesByCity = retrofitInstance.getMoviesByCity(city)
        getMoviesByCity.enqueue(object : Callback<MovieData>{
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if(response.isSuccessful) {
                    val data = response.body()

                    if(!(data.isNullOrEmpty())) {
                        Log.i("response", "onResponse: $response")
                        adapter = DisplayMoviesAdapter(data)
                        recyclerView.adapter = adapter
                    } else {
                        Toast.makeText(requireContext(), "No Movie is on site in this Location", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Something Went Wrong Please try Again", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}