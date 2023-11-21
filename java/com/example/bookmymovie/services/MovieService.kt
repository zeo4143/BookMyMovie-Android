package com.example.bookmymovie.services


import com.example.bookmymovie.models.CitySchema
import com.example.bookmymovie.models.MovieData
import com.example.bookmymovie.models.ShowsByDate
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    //Route

    @GET("/city")
    fun getAllCities() : Call<CitySchema>

    @GET("/movie/{city}")
    fun getMoviesByCity(@Path("city") city: String) : Call<MovieData>

    @GET("/shows/{city}/{movieId}/{language}/{date}")
    fun getShowsByMovieAndDate(@Path("city") city: String, @Path("movieId") movieId:String,@Path("language") language:String, @Path("date") date:String) : Call<ShowsByDate>




    //object created to fetch from url.

    object RetrofitObjectBuild {

        val retrofitInstance  : MovieService
        init {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://bookmymovie-kbrb.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofitInstance = retrofit.create(MovieService::class.java)
        }
    }
}