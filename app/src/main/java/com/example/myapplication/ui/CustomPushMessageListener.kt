package com.example.myapplication.ui

import android.app.Activity
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.startActivity
import com.moengage.pushbase.model.NotificationPayload
import com.moengage.pushbase.push.PushMessageListener

class CustomPushMessageListener : PushMessageListener() {

    override fun customizeNotification(
        notification: Notification, context: Context, payload: Bundle
    ) {
        Log.d("cpml", "customizeNotification: $payload")
        super.customizeNotification(notification, context, payload)
    }

    override fun getIntentFlags(payload: Bundle): Int {
        Log.d("cpml", "getIntentFlags: $payload")
        return super.getIntentFlags(payload)
    }

    override fun handleCustomAction(context: Context, payload: String) {
        Log.d("cpml", "handleCustomAction: $payload")
        super.handleCustomAction(context, payload)
    }

    override fun isNotificationRequired(context: Context, payload: Bundle): Boolean {
        Log.d("cpml", "isNotificationRequired: $payload")
        return super.isNotificationRequired(context, payload)
    }

    override fun onCreateNotification(
        context: Context, notificationPayload: NotificationPayload
    ): NotificationCompat.Builder? {

        Log.d("cpml", "onCreateNotification: $notificationPayload")


//        return MyNotificationBuilder.showMyNotification(context, title, content, null)

//
//
//            val pii = PendingIntent.getActivity(
//                context.applicationContext, 0, Intent(), PendingIntent.FLAG_IMMUTABLE
//            )
//
//            val myUser = notificationPayload.payload.getString("user")
//
//            if (myUser == "true") {
//                return MyNotificationBuilder.showNotification(
//                    context, title = mytitle, body = mybody, pii
//                )
//            }

//        val notificationManager =
//            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
//            val notificationChannel =
//                NotificationChannel("123", "beep", NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//
//        val myNotificationBuilder = NotificationCompat.Builder(
//            context.applicationContext, "123"
//        ).setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle(notificationPayload.text.title)
//            .setContentText(notificationPayload.text.message)
//
//        notificationManager.notify(123, myNotificationBuilder.build())

//        return super.onCreateNotification(context, notificationPayload)

//        val payloadData = notificationPayload.payload
//
//        // Check if the key-value pair exists and set your condition
//        val myKey = payloadData.getString("isNotif") // Replace "some_key" with your key name
//        if (myKey == "true") { // Replace "expected_value" with the value you're checking for
//            return MyNotificationBuilder.showMyNotification(
//                context, notificationPayload.text.title, notificationPayload.text.message, null
//            )
//        } else {
//            // If the key-value pair doesn't match, do not show the notification
//            Log.d("fbs", "Notification suppressed due to unmatched key-value pair")
//            return null
//        }

        return super.onCreateNotification(context, notificationPayload)

    }

    override fun onNotificationCleared(context: Context, payload: Bundle) {
        Log.d("cpml", "onNotificationCleared: $payload")

        super.onNotificationCleared(context, payload)
    }

    override fun onNotificationClick(activity: Activity, payload: Bundle): Boolean {
        Log.d("cpml", "onNotificationClick payload: $payload")

//        val myUrl = payload.getString("url")
////
//        val intent = Intent(activity, CustomWebView::class.java)
//        intent.putExtra("url", myUrl)
////        intent.putExtra("url-value", myUrl)
////        Log.d("cmpl intent", "gcm url set intent extra: $url_value")
////        Log.d("cmpl intent", "gcm url set intent extra: $myUrl")
//        startActivity(activity, intent, null)



        return true
    }

    override fun onNotificationReceived(context: Context, payload: Bundle) {
        Log.d("fbs", "onNotificationReceived payload: $payload")
        super.onNotificationReceived(context, payload)
    }

    override fun onPostNotificationReceived(context: Context, payload: Bundle) {
        Log.d("fbs", "onPostNotificationReceived: $payload")
        super.onPostNotificationReceived(context, payload)
    }
}