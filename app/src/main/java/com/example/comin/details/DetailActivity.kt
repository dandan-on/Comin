package com.example.comin.details

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import com.example.comin.R
import com.example.comin.fragment.marketinfo.ReviewFragment
import com.example.comin.utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name: String? = intent.getStringExtra( "name")
        detail_restaurant_name.text = name

        //찜 여부 확인
//        FirebaseUtils.db
//                .collection("restaurants")
//                .document(FirebaseUtils.getUid())
//                .get()
//                .addOnSuccessListener { documentSnapshot ->
//                    if(name?.let { documentSnapshot.get(it) } == true){
//                        detail_zzim.text = "이 가게 찜했음♥"
//                        detail_zzim.setTextColor(Color.RED)
//                    }
//                }

        var documentId: String? = ""
        FirebaseUtils.db
                .collection("restaurants")
                .whereEqualTo("name", name)
                .get()
                .addOnSuccessListener { documents ->
                    for ( document in documents ){
                        if (document.data["zzim"] == true) {
                            detail_zzim_txt.text = "찜했음"
                            detail_zzim_img.setImageResource(R.drawable.ic_baseline_favorite_24)
                        }
                        //하나의 가게만 검색된다고 가정(원래는 가게 이름이 아니라 아이디로 검색하는게 맞음)
                        documentId = document.id
                    }
                }

        detail_zzim_area.setOnClickListener {

            if (detail_zzim_txt.text == "찜했음"){
                //이미 찜 되어 있을 때
                detail_zzim_txt.text = "찜하기"
                detail_zzim_img.setImageResource(R.drawable.ic_baseline_favorite_border_24)

                if (name != null && documentId != null) {
                    FirebaseUtils.db
                        .collection("restaurants")
                        .document(documentId!!)
                        .update("zzim", false)
                        .addOnSuccessListener {
                            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                        }
                }

            } else {
                //찜 되어 있지 않을 때
                detail_zzim_txt.text = "찜했음"
                detail_zzim_img.setImageResource(R.drawable.ic_baseline_favorite_24)

                if (name != null && documentId != null) {
                    FirebaseUtils.db
                        .collection("restaurants")
                        .document(documentId!!)
                        .update("zzim", true)
                        .addOnSuccessListener {
                            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                        }
                }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.detail_fragment_area, ContentFragment())
            .commit()

        detail_menu_btn.setOnClickListener {
            
            detail_menu_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
            detail_info_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            detail_review_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_fragment_area, ContentFragment())
                .commit()
        }

        detail_info_btn.setOnClickListener {
            detail_menu_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            detail_info_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
            detail_review_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_fragment_area, InfoFragment())
                .commit()
        }

        detail_review_btn.setOnClickListener {
            detail_menu_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            detail_info_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            detail_review_btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_fragment_area, ReviewFragment())
                .commit()
        }

    }
}
