package com.example.comin.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.comin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.activity_join.*


class JoinActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = FirebaseAuth.getInstance()
        Log.e("JoinActivity", "성공1")

        join_button.setOnClickListener {
            val email : String = join_email_area.text.toString().trim()
            val password : String = join_password_area.text.toString().trim()
//            if(TextUtils.isEmpty(email)){
//                Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show()
//            }
//            if(TextUtils.isEmpty(password)){
//                Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->

                    println(task.isSuccessful) //false가 떠요!
                    if(task.isSuccessful){
                        Log.d("JoinActivity", "성공2")
                        //finish()
                        val intent = Intent(this, JoinInfoActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.d("JoinActivity", "실패")
                        Toast.makeText(this,"fail",Toast.LENGTH_LONG).show()
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(applicationContext, "Invalid Password", Toast.LENGTH_LONG).show()
                        } catch (e: FirebaseAuthEmailException) {
                            Toast.makeText(applicationContext, "Invalid Email", Toast.LENGTH_LONG).show()
                        } catch (e: FirebaseAuthException) {
                            Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_LONG).show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
        }

    }
}

//happylife@gmail.com
//happyhappy1234