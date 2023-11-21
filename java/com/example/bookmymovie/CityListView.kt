package com.example.bookmymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.bookmymovie.models.CitySchema
import com.example.bookmymovie.services.MovieService.RetrofitObjectBuild.retrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityListView : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var adapter: CityListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list_view)

        listView = findViewById(R.id.listView)

        getAllCities()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun getAllCities() {

        val getAllCities = retrofitInstance.getAllCities()
        getAllCities.enqueue(object : Callback<CitySchema> {
            override fun onResponse(call: Call<CitySchema>, response: Response<CitySchema>) {
                if(response.isSuccessful) {
                    val data = response.body()!!
                    adapter = CityListViewAdapter(this@CityListView, data)
                    listView.adapter = adapter
                    Log.i("cities", "onResponse: $data")
                } else {
                    Toast.makeText(this@CityListView, "Something went Wrong", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CitySchema>, t: Throwable) {
                Toast.makeText(this@CityListView, "Connect to your Internet", Toast.LENGTH_SHORT).show()
            }

        })

    }
}