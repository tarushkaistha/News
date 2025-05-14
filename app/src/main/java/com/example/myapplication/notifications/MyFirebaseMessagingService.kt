package com.example.myapplication.notifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.firebase.MoEFireBaseHelper


class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)


//        Log.d("service", "on token observe: $token ")
//
        MoEFireBaseHelper.getInstance().passPushToken(applicationContext, "")
//
//        Log.d("service", "on pass psh token observe: $token ")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


    }
}

