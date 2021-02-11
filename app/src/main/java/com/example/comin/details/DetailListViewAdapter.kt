package com.example.comin.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.comin.R
import com.example.comin.restaurants.adapter.RestaurantsListViewAdapter

class DetailListViewAdapter(
    val context: Context,
    val priceList : ArrayList<String>,
    val nameList : ArrayList<String>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_menu_item, null)
            holder = ViewHolder()
            holder.view_image = view.findViewById(R.id.lv_menu_image)
            holder.view_name = view.findViewById(R.id.lv_menu_name)
            holder.view_ingredient = view.findViewById(R.id.lv_menu_ingre)
            holder.view_price = view.findViewById(R.id.lv_menu_price)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        holder.view_name?.text  = nameList[position]
        holder.view_price?.text = priceList[position]

        return view
    }

    override fun getItem(position: Int): Any = 0

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = nameList.size

    private class ViewHolder{

        var view_image : ImageView? = null
        var view_name : TextView? = null
        var view_ingredient : TextView? = null
        var view_price : TextView? = null

    }
}