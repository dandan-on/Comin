package com.example.comin.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.comin.R
import kotlinx.android.synthetic.main.home_gridview_item.view.*

class HomeGridViewAdapter (
        val context : Context,
        val imageList : ArrayList<Int>,
        val textList : ArrayList<String> ) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view : View = LayoutInflater.from(context).inflate(R.layout.home_gridview_item, null)

        view.home_gridview_text.text = textList[position]
        view.home_gridview_img.setImageResource(imageList[position])

        return view
    }

    override fun getItem(position: Int): Any = 0

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int =  imageList.size

}