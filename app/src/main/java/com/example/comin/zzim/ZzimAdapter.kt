package com.example.comin.zzim

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.comin.R
import kotlinx.android.synthetic.main.zzim_item.view.*

class ZzimAdapter(val context: Context, val zzimList : ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int = zzimList.size

    override fun getItem(position: Int): Any = 0

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.zzim_item, null)
        view.zzim_text.text = zzimList[position]
        return view
    }
}