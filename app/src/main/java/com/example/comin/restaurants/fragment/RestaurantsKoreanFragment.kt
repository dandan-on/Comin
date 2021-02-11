package com.example.comin.restaurants.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.comin.model.RestaurantModel
import com.example.comin.R
import com.example.comin.details.DetailActivity
import com.example.comin.restaurants.adapter.RestaurantsListViewAdapter
import com.example.comin.utils.FirebaseUtils
import kotlinx.android.synthetic.main.fragment_restaurants.view.*
import kotlinx.android.synthetic.main.fragment_restaurants_chicken.*
import kotlinx.android.synthetic.main.fragment_restaurants_chicken.view.*
import kotlinx.android.synthetic.main.fragment_restaurants_chinese.*
import kotlinx.android.synthetic.main.fragment_restaurants_chinese.view.*
import kotlinx.android.synthetic.main.fragment_restaurants_fastfood.*
import kotlinx.android.synthetic.main.fragment_restaurants_fastfood.view.*
import kotlinx.android.synthetic.main.fragment_restaurants_japanese.*
import kotlinx.android.synthetic.main.fragment_restaurants_japanese.view.*
import kotlinx.android.synthetic.main.fragment_restaurants_korean.*
import kotlinx.android.synthetic.main.fragment_restaurants_korean.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantsKoreanFragment(val category: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //버전2
//        val layout_name = when(keyword) {
//            "패스트푸드" -> R.layout.fragment_restaurants_fastfood
//            "한식" -> R.layout.fragment_restaurants_korean
//            "치킨" -> R.layout.fragment_restaurants_chicken
//            "중식" -> R.layout.fragment_restaurants_chinese
//            else -> R.layout.fragment_restaurants_japanese
//        }

        //버전3 cf) 버전2에서는 첫번째 파라미터에 layout_name 변수가 들어감
        val view : View =  inflater.inflate(R.layout.fragment_restaurants, container, false)

        //버전2
//        val listview_id = when(keyword) {
//            "패스트푸드" ->  view.lv_fragment_fastfood
//            "한식" -> view.lv_fragment_korean
//            "치킨" -> view.lv_fragment_chicken
//            "중식" ->  view.lv_fragment_chinese
//            else -> view.lv_fragment_japanese
//        }

        val restaurantList = arrayListOf<RestaurantModel>()

        FirebaseUtils.db
            .collection("restaurants")
            .whereEqualTo("category", category)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    restaurantList.add(RestaurantModel(R.drawable.pizza, document.data["name"] as String, 1, document.data["category"] as String))
                }

                val adapter = RestaurantsListViewAdapter(requireContext(), restaurantList)
                //버전3 cf_ 버전2에서는 view.~~~.에 listview_id 변수가 들어감
                view.lv_fragment_restaurants.adapter = adapter
                //위 두줄을 리스너 바깥에서 실행하게 되면 list에 아무것도 담기지 않음. 리스너 안에서 adapter를 넘겨줘야 함.
            }
           .addOnFailureListener {
                Toast.makeText(requireContext(), "실패", Toast.LENGTH_LONG).show()
            }

        view.lv_fragment_restaurants.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("name", restaurantList[position].name)
            startActivity(intent)
        }
        return view
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment SecondFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            RestaurantsKoreanFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}