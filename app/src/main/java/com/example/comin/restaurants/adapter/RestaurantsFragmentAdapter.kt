package com.example.comin.restaurants.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.comin.restaurants.fragment.*
import kotlinx.android.synthetic.main.activity_restaurants.*


class RestaurantsFragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 8

    override fun createFragment(position: Int): Fragment {
        val category = when(position) {
            0 -> "패스트푸드"
            1 -> "한식"
            2 -> "치킨"
            3 -> "찜/탕"
            4 -> "중식"
            5 -> "일식"
            6 -> "양식"
            else -> "디저트"
        }
        return RestaurantsKoreanFragment(category)
    }
}

//버전1 : fragment와 layout 각각 만들기
//when(position) {
//    0 -> { RestaurantsFastFoodFragment() }
//    1 -> { RestaurantsKoreanFragment() }
//    2 -> { RestaurantsChickenFragment() }
//    3 -> { RestaurantsChinaFragment() }
//    else -> { RestaurantsJapanFragment() }
//}

//버전2 : layout은 각각 만들고, frament는 하나만 사용 (category를 통해 layout 구분)
//버전3 : frament와 layout을 하나만 만들기