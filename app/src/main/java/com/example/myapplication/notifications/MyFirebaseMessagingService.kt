package com.example.myapplication.notifications

import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("service", "onNewToken observe: $token ")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        handleMessage(message)


    }

    private fun handleMessage(remoteMessage: RemoteMessage) {
        val handler = android.os.Handler(Looper.getMainLooper())
        handler.post {
            remoteMessage.data.isNotEmpty().let {
                Toast.makeText(baseContext, remoteMessage.data.toString(), Toast.LENGTH_LONG).show()

            }
        }
    }


}