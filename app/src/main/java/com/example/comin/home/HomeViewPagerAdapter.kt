 package com.example.comin.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.comin.R

class HomeViewPagerAdapter(private val context: Context) : RecyclerView.Adapter<HomeViewPagerAdapter.HomePagerViewHolder>() {
    private var layoutInflater : LayoutInflater? = null

    private val imageList = arrayOf(
            R.drawable.ssingeat_photo1,
            R.drawable.ssingeat_photo2,
            R.drawable.ssingeat_photo3
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePagerViewHolder =
            HomePagerViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_home_viewpager, parent, false))

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: HomePagerViewHolder, position: Int) {
//        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val v = layoutInflater!!.inflate(R.layout.activity_home_viewpager, null)
//        val image = v.findViewById<View>(R.id.imageView) as ImageView
        holder.bind(imageList[position])
    }

    inner class HomePagerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageView)

        fun bind(imgResource : Int) {
            imageView.setImageResource(imgResource)
        }
    }

}