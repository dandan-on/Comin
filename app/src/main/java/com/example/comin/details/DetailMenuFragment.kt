package com.example.comin.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.comin.R
import kotlinx.android.synthetic.main.fragment_detail_menu.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val priceList = ArrayList<String>()
    val nameList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_detail_menu, container, false)

        priceList.add("5000원")
        priceList.add("8000원")
        priceList.add("10000원")
        priceList.add("5000원")
        priceList.add("8000원")
        priceList.add("10000원")

        nameList.add("111메뉴111")
        nameList.add("222메뉴222")
        nameList.add("333메뉴333")
        nameList.add("444메뉴444")
        nameList.add("555메뉴555")
        nameList.add("666메뉴666")

        val list_adapter = DetailListViewAdapter(requireContext(), priceList, nameList)
        view.lv_detail_menu.adapter = list_adapter
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ContentFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}