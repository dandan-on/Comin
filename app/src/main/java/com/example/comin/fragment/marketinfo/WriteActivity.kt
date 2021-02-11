package com.example.comin.fragment.marketinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comin.home.MainActivity
import com.example.comin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity() {

    private lateinit var  auth : FirebaseAuth
    private val db = FirebaseFirestore.getInstance()
    private lateinit var nickname : String
    private lateinit var ratingNum : String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        auth = FirebaseAuth.getInstance()

        //닉네임이 저장된 경로
        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())
        docRef.get().addOnSuccessListener { documentSnapshot ->
            nickname = documentSnapshot.get("nickname") as String
        }

        //rating
        rating_area.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingNum = rating.toString()
        }

        writing_button.setOnClickListener {

            val form = hashMapOf(
                    "text" to text_input_area.text.toString(),
                    "writer" to nickname,
                    "rating" to ratingNum
            )
            db.collection("reviews")
                    .add(form)
                    .addOnSuccessListener {
                        Toast.makeText(this,"성공",Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { Toast.makeText(this,"실패",Toast.LENGTH_LONG).show() }
        }


    }
}