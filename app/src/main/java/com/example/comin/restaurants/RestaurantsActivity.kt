package com.example.comin.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.get
import com.example.comin.R
import com.example.comin.restaurants.adapter.RestaurantsFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_restaurants.*
import kotlinx.android.synthetic.main.custom_tab.view.*


class RestaurantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        val category: String? = intent.getStringExtra( "category")
        val position: Int = intent.getIntExtra("position", 0)

        val fragmentAdapter = RestaurantsFragmentAdapter(this)
        restaurants_viewPager.adapter = fragmentAdapter
        restaurants_viewPager.currentItem = position //gridView에서 선택한 카테고리에 해당하는 fragment가 보이도록

        val tabViews = arrayOf("패스트\n푸드", "한식", "치킨", "찜/탕", "중식", "일식", "양식", "디저트")

        TabLayoutMediator(restaurants_tabLayout, restaurants_viewPager) { tab, position ->
            tab.setCustomView(createTabView(tabViews[position]))
        }.attach()

        restaurants_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    restaurants_viewPager.currentItem = tab.position
                }
            }

        })
    }

    private fun createTabView(tabName : String) : View {
        val tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)
        tabView.tab_name.text = tabName
        return tabView
    }

}
