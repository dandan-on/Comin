package com.example.comin.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.comin.home.MainActivity
import com.example.comin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_join_info.*

class JoinInfoActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_info)

        auth = FirebaseAuth.getInstance()

        join_info_button.setOnClickListener {
            val user = hashMapOf(
                "nickname" to nickname_area.text.toString()
            )

            db.collection("users")
                .document(auth.currentUser?.uid.toString())
                .set(user)
                .addOnSuccessListener {
                    Log.e("JoinInfoActivity", "성공")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { Log.e("JoinInfoActivity","실패") }
        }


    }
}