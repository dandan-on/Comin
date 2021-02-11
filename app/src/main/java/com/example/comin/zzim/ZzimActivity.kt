package com.example.comin.zzim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.comin.R
import com.example.comin.utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_zzim.*

class ZzimActivity : AppCompatActivity() {

    val marketNameList = arrayListOf<String>("맛있는 케이크", "달달한 케이크", "몸에 좋은 케이크",
        "케이크 같은 케이크", "먹으면 행복한 케이크", "딜리셔스한 케이크", "안녕 케이크")
    val zzimList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zzim)
//
//        FirebaseUtils.db
//            .collection("zzim")
//            .document(FirebaseUtils.getUid())
//            .get()
//            .addOnSuccessListener { documentSnapshot ->
//
//                for ( marketName in marketNameList ){
//                    if (documentSnapshot.get(marketName)!=""){
//                        zzimList.add(marketName)
//                    }
//                }
//
//                val zzimAdapter = ZzimAdapter(this, zzimList)
//                zzim_listview.adapter = zzimAdapter
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
//            }

        FirebaseUtils.db
                .collection("restaurants")
                .whereEqualTo("zzim", true)
                .get()
                .addOnSuccessListener { documents ->
                    for ( document in documents) {
                        zzimList.add(document.get("name") as String)
                    }

                    val zzimAdapter = ZzimAdapter(this, zzimList)
                    zzim_listview.adapter = zzimAdapter
                }
                .addOnFailureListener {
                    Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                }




    }
}