package com.example.comin.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUtils {

    companion object {

        private var auth : FirebaseAuth = FirebaseAuth.getInstance()
        var db : FirebaseFirestore = FirebaseFirestore.getInstance()

        fun getUid() : String {
            return auth.currentUser?.uid.toString()
        }

    }

}