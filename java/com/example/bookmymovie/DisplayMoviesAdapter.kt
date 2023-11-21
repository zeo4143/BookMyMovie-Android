package com.example.bookmymovie

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmymovie.models.MovieDataItem


class DisplayMoviesAdapter(private var movies: List<MovieDataItem>): RecyclerView.Adapter<DisplayMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.display_moives_grid_layout,parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movie = movies[position]

        //Binding Data to the views in the ViewHolder
        holder.title.text = movie.title
        holder.movieType.text = movie.movieType
        holder.language.text = concatenateStrings(movie.language)
        Glide.with(holder.movieImage.context).load("https://bookmymovie-kbrb.onrender.com/images/${movie.images[1]}").into(holder.movieImage)

        //adding onClickListener to cardView
        holder.itemView.setOnClickListener {
//            Log.i("putExtra", "onBindViewHolder: $movie")
           val intent = Intent(holder.itemView.context,SpecificMovieShows::class.java)
            intent.putExtra("movieDetails", movie)
            holder.itemView.context.startActivity(intent)

            if(movie.language.size > 1) {
                var dialog = Dialog(holder.itemView.context)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                var a = LayoutInflater.from(holder.itemView.context).inflate(R.layout.activity_languages_pop_up , null)

                dialog.setContentView(a)
                val button = a.findViewById<Button>(R.id.choose_language)

                button.setOnClickListener {
                    val intent = Intent(holder.itemView.context,SpecificMovieShows::class.java)
                    intent.putExtra("movieDetails", movie)
                    holder.itemView.context.startActivity(intent)

                    (holder.itemView.context as AppCompatActivity).finish()

                }
                dialog.create()

                dialog.show()
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.window?.setGravity(Gravity.BOTTOM)


            }
            else {
                val intent = Intent(holder.itemView.context,SpecificMovieShows::class.java)
                intent.putExtra("movieDetails", movie)
                holder.itemView.context.startActivity(intent)

            }
//
        }

    }

    inner class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.display_movie_image)
        val title: TextView = itemView.findViewById(R.id.display_movie_title)
        val language: TextView = itemView.findViewById(R.id.display_languages)
        val movieType: TextView = itemView.findViewById(R.id.display_movie_type)
    }

    private fun concatenateStrings(list: List<String>): String {
        return list.joinToString(" | ")
    }

}

