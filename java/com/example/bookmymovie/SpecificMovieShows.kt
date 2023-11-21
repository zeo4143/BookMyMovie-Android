package com.example.bookmymovie

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmymovie.models.MovieDataItem
import com.example.bookmymovie.models.ShowsByDate
import com.example.bookmymovie.services.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class SpecificMovieShows : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var playButton: ImageButton
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var  sharedPreferences: SharedPreferences
    private lateinit var movieDescription:TextView

    //RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TheatrePreviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_movie_shows)

        //view Binders
        movieDescription = findViewById(R.id.specific_movie_description)
        constraintLayout = findViewById(R.id.specific_movie_constraint_layout_child)
        recyclerView = findViewById(R.id.specific_movie_constraint_layout_recyclerView)



        sharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE)
        val city = sharedPreferences.getString("cityId", "Jalandhar").toString()

        val receivedData:MovieDataItem = intent.getParcelableExtra("movieDetails")!!
        
        if(receivedData != null) {
            Log.i("movieData", "onCreate: $receivedData")
            getShowsByDate(city, receivedData._id, language = "Tamil", date = "05-11-2023")
            movieDescription.text = receivedData.description

        } else {
            Toast.makeText(this, "Something Went Wrong please go back and try again", Toast.LENGTH_SHORT).show()
        }


        webView = findViewById(R.id.specific_movie_webView)
        playButton = findViewById(R.id.specific_movie_play_button)

        // Configure WebView settings (enable JavaScript, etc.)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        playButton.setOnClickListener {
            // Replace with the actual URL
            webView.loadUrl(receivedData.videoURL)

        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        super.onBackPressed()
    }

    //fetch specific movie data and preview cinema and show timings

    private fun getShowsByDate(city: String, movieId:String, language:String, date:String) {
        val getShowsByDate = MovieService.RetrofitObjectBuild.retrofitInstance.getShowsByMovieAndDate(city, movieId,language,date)

        getShowsByDate.enqueue(object : Callback<ShowsByDate> {
            override fun onResponse(call: Call<ShowsByDate>, response: Response<ShowsByDate>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    if(!(data.isNullOrEmpty())) {
                        Log.i("shows", "onResponse: $data")
                        adapter = TheatrePreviewAdapter(data)
                        recyclerView.adapter = adapter
                    } else {
                        Toast.makeText(this@SpecificMovieShows, "No Movie is on site in this Location", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SpecificMovieShows, "Something Went Wrong Please try Again", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ShowsByDate>, t: Throwable) {
                Toast.makeText(this@SpecificMovieShows, "Something Went Wrong Please try Again", Toast.LENGTH_SHORT).show()
            }
        })
    }
}