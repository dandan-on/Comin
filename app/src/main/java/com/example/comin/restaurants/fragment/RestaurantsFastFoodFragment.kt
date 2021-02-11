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
import kotlinx.android.synthetic.main.fragment_restaurants_fastfood.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantsFastFoodFragment : Fragment() {
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

        val view : View =  inflater.inflate(R.layout.fragment_restaurants_fastfood, container, false)

        val restaurantList = arrayListOf<RestaurantModel>()

        FirebaseUtils.db
                .collection("restaurants")
                .whereEqualTo("category", "패스트푸드")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        restaurantList.add(RestaurantModel(R.drawable.pizza, document.data["name"] as String, 1, document.data["category"] as String))
                    }
                    val adapter = RestaurantsListViewAdapter(requireContext(), restaurantList)
                    view.lv_fragment_fastfood.adapter = adapter
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "실패", Toast.LENGTH_LONG).show()
                }

//        FirebaseUtils.db
//                .collection("zzim")
//                .document(FirebaseUtils.getUid())
//                .get()
//                .addOnSuccessListener { documentSnapshot ->
//                    if (documentSnapshot.exists() == true){
//                        //Data 필드가 있을 때
//
//                    } else {
//                        //Data 필드가 없을 때
//                        val lecture = hashMapOf(
//                                "맛있는 케이크" to "",
//                                "달달한 케이크" to "",
//                                "몸에 좋은 케이크" to "",
//                                "케이크 같은 케이크" to "",
//                                "먹으면 행복한 케이크" to "",
//                                "딜리셔스한 케이크" to "",
//                                "안녕 케이크" to ""
//                        )
//
//                        FirebaseUtils.db
//                                .collection("zzim")
//                                .document(FirebaseUtils.getUid())
//                                .set(lecture)
//                                .addOnSuccessListener {  }
//                                .addOnFailureListener {  }
//                    }
//
//                }

        view.lv_fragment_fastfood.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("title", restaurantList[position].name)
            startActivity(intent)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RestaurantsFastFoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}