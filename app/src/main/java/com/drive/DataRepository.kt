package com.drive

import com.drive.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class DataRepository private constructor() {
    companion object {
        private var instance: DataRepository? = null
        val auth by lazy { FirebaseAuth.getInstance() }
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        var user = User()


        fun getNewInstance() {
                instance = DataRepository()
                instance
        }

        fun getUserFromFireBase() {
            Firebase.firestore
                .collection("studies")
                .document(firebaseUser!!.uid)
                .get()
                .addOnSuccessListener {
                    user = it.toObject<User>()!!
                }
        }
    }
}