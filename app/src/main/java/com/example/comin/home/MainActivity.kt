package com.example.comin.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.viewpager2.widget.ViewPager2
import com.example.comin.restaurants.RestaurantsActivity
import com.example.comin.R
import com.example.comin.zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*
import kotlinx.android.synthetic.main.bottom_navigation.*

class MainActivity : AppCompatActivity() {

    private lateinit var  home_viewPager : ViewPager2
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val categoryImgList = arrayListOf(
                R.drawable.fastfood,
                R.drawable.kimchi,
                R.drawable.chicken,
                R.drawable.soup,
                R.drawable.chinesefood,
                R.drawable.sushi,
                R.drawable.spaguetti,
                R.drawable.cake
        )

        val categoryTextList = arrayListOf(
            "패스트푸드", "한식", "치킨", "찜/탕", "중식", "일식", "양식", "디저트"
        )

        home_viewPager = findViewById<ViewPager2>(R.id.home_viewPager)
        val adapter = HomeViewPagerAdapter(this)
        home_viewPager.adapter = adapter

        val gridViewAdapter = HomeGridViewAdapter(this, categoryImgList, categoryTextList)
        home_gridView.adapter = gridViewAdapter
        home_gridView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, RestaurantsActivity::class.java)
            intent.putExtra("category", categoryTextList[position])
            intent.putExtra("position", position)
            startActivity(intent)
        })

//        my_page_icon.setOnClickListener {
//            if(auth.currentUser == null){
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }else {
//                //Firebase.auth.signOut()
//                val intent = Intent(this, MyCominActivity::class.java)
//                startActivity(intent)
//            }
//        }
//
        bottom_navigationView.setOnNavigationItemSelectedListener {
            val intent = when(it.itemId) {
                R.id.bottom_home -> Intent(this, MainActivity::class.java)
                R.id.bottom_zzim -> Intent(this, ZzimActivity::class.java)
                R.id.bottom_mypage -> Intent(this, MainActivity::class.java)
                else -> Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            return@setOnNavigationItemSelectedListener true
        }

//        bottom_zzim.setOnClickListener {
//            val intent = Intent(this, ZzimActivity::class.java)
//            startActivity(intent)
//        }
    }

}