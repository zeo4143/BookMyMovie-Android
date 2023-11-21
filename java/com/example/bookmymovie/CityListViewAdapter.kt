package com.example.bookmymovie

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmymovie.models.CitySchema
import com.example.bookmymovie.models.CitySchemaItem

class CityListViewAdapter(private val context: Context, private val items: CitySchema) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.city_list_view_item, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position) as CitySchemaItem
        viewHolder.textView.text = item.city_name

        view.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)

            // Pass data to the next activity
            val sharedPreferences = context.getSharedPreferences("Credentials", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("cityId", item._id)
            editor.apply()

            context.startActivity(intent)

            // Finish the current activity
            (context as AppCompatActivity).finish()
        }

        return view
    }
}

private class ViewHolder(view: View) {
    val textView: TextView = view.findViewById(R.id.list_view_item)
}
