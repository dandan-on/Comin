package com.example.comin.fragment.marketinfo

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.comin.R
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewListAdapter(
        val context: Context,
        val list_nickname : ArrayList<String>,
        val list_text : ArrayList<String>,
        val list_rating : ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int = list_nickname.size

    override fun getItem(position: Int): Any = 0


    override fun getItemId(position: Int): Long = 0



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.review_item, null)
        view.review_nickname.text = list_nickname[position]
        view.review_content.text = list_text[position]
        view.review_rating.text = list_rating[position]
        return view
    }


}