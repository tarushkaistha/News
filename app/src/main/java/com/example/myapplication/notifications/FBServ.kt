package com.example.myapplication.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FBServ : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("firebase", "onMessageReceived: ${message.data}")
        println("remote data : ${message.data}")
        Log.d("firebase", "onMessageReceived: ${message.notification}")
        println("notification data : ${message.notification}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    "firebase", "Fetching FCM registration token failed", task.exception
                )
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("firebase", "Resultant token: $token")

        }

    }

}