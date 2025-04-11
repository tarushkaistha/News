package com.example.myapplication.ui

import android.app.Activity
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.startActivity
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.pushbase.model.NotificationPayload
import com.moengage.pushbase.push.PushMessageListener

class CustomPushMessageListener : PushMessageListener() {

    override fun customizeNotification(
        notification: Notification, context: Context, payload: Bundle
    ) {
        Log.d("fbs", "customizeNotification: $payload")
        super.customizeNotification(notification, context, payload)
    }

    override fun getIntentFlags(payload: Bundle): Int {
        Log.d("fbs", "getIntentFlags: $payload")
        return super.getIntentFlags(payload)
    }

    override fun handleCustomAction(context: Context, payload: String) {
        Log.d("fbs", "handleCustomAction: $payload")
        super.handleCustomAction(context, payload)
    }

    override fun isNotificationRequired(context: Context, payload: Bundle): Boolean {
        Log.d("fbs", "isNotificationRequired: $payload")
        return super.isNotificationRequired(context, payload)
    }

//    override fun onCreateNotification(
//        context: Context, notificationPayload: NotificationPayload
//    ): NotificationCompat.Builder? {
//
//        Log.d("fbs", "onCreateNotification: $notificationPayload")
////            val mytitle = notificationPayload.payload.getString("gcm_title")
////            val mybody = notificationPayload.payload.getString("gcm_alert")
////
////
////            val pii = PendingIntent.getActivity(
////                context.applicationContext, 0, Intent(), PendingIntent.FLAG_IMMUTABLE
////            )
////
////            val myUser = notificationPayload.payload.getString("user")
////
////            if (myUser == "true") {
////                return MyNotificationBuilder.showNotification(
////                    context, title = mytitle, body = mybody, pii
////                )
////            }
//
//        return super.onCreateNotification(context, notificationPayload)
//    }

    override fun onNotificationCleared(context: Context, payload: Bundle) {
        Log.d("fbs", "onNotificationCleared: $payload")

        super.onNotificationCleared(context, payload)
    }

//    override fun onNotificationClick(activity: Activity, payload: Bundle): Boolean {
//        Log.d("fbs", "onNotificationClick: $payload")
//
//        val url_value = payload.getString("gcm_webUrl")
//        Log.d("cmpl", "gcm url: $url_value")
//
//        val myUrl = payload.getString("url")
//
//        val property = Properties()
//        property.addAttribute("mydeeplink", myUrl)
//        MoEAnalyticsHelper.trackEvent(activity, "tap on pn", property)
////
//        val intent = Intent(activity, CustomWebView::class.java)
//        intent.putExtra("url", url_value)
////        intent.putExtra("url-value", myUrl)
////        Log.d("cmpl intent", "gcm url set intent extra: $url_value")
////        Log.d("cmpl intent", "gcm url set intent extra: $myUrl")
//        startActivity(activity, intent, null)
//
//
//
//        return false
//    }

    override fun onNotificationReceived(context: Context, payload: Bundle) {
        Log.d("fbs", "onNotificationReceived: $payload")
        super.onNotificationReceived(context, payload)
    }

    override fun onPostNotificationReceived(context: Context, payload: Bundle) {
        Log.d("fbs", "onPostNotificationReceived: $payload")
        super.onPostNotificationReceived(context, payload)
    }
}