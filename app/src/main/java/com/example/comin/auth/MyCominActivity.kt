package com.example.comin.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_my_comin.*

class MyCominActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_comin)

        auth = FirebaseAuth.getInstance()
        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot ->
            nickname_area.text = documentSnapshot.get("nickname").toString()
        }


    }
}