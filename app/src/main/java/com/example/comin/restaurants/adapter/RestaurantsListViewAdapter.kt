package com.example.comin.restaurants.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.comin.model.RestaurantModel
import com.example.comin.R

class RestaurantsListViewAdapter (val context : Context, val list : ArrayList<RestaurantModel>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view : View
        val holder : ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_item, null)
            holder = ViewHolder()
            holder.view_image = view.findViewById(R.id.lv_image)
            holder.view_title = view.findViewById(R.id.lv_restaurant_name)
            holder.view_number = view.findViewById(R.id.lv_rating)
            holder.view_category = view.findViewById(R.id.lv_category)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val item = list[position]
        holder.view_image?.setImageResource(item.imageUrl)
        holder.view_title?.text  = item.name
        holder.view_category?.text = item.category

        return view
    }

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = list.size

    private class ViewHolder{

        var view_image : ImageView? = null
        var view_title : TextView? = null
        var view_number : TextView? = null
        var view_category : TextView? = null

    }
}