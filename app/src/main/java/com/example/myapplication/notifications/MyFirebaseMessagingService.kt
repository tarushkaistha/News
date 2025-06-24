package com.example.myapplication.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.ui.TestActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.firebase.MoEFireBaseHelper


class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)


        MoEFireBaseHelper.getInstance().passPushToken(applicationContext, token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        handleMessage(message)


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleMessage(remoteMessage: RemoteMessage) {
        val handler = android.os.Handler(Looper.getMainLooper())
        handler.post {

            val bundle = Bundle()
            for ((key, value) in remoteMessage.data.entries) {
                bundle.putString(key, value)
            }
//            bundle.putString("mytitle", title)
//            bundle.putAll()
//            bundle.putString("mycontent", content)
            val intent = Intent(applicationContext, TestActivity::class.java).apply {
//                putExtra("title",title)
//                putExtra("content",content)
                putExtras(bundle)
            }
            val pendingIntent = PendingIntent.getActivity(
                applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )

            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, remoteMessage.data)

//            if (MoEPushHelper.getInstance().isFromMoEngagePlatform(remoteMessage.data)) {
//                MoEFireBaseHelper.getInstance()
//                    .passPushPayload(applicationContext, remoteMessage.data)
//                return@post
//            }


        }
    }

//    private fun showNotification(
//        title: String?, body: String?, pii: PendingIntent?
//    ): NotificationCompat.Builder {
//
//
//        val myNotificationView = RemoteViews(packageName, R.layout.add_btn_in_custom_notification)
//
//        myNotificationView.setOnClickPendingIntent(R.id.clickButton, pii)
//
//
//        val notificationManager = notificationManager()
//
//        val myNotificationBuilder = NotificationCompat.Builder(
//            applicationContext, "123"
//        ).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title)
//            .setContentText(body).setContentIntent(pii).setDefaults(Notification.DEFAULT_ALL)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)/*.setCustomContentView(myNotificationView)*/
//
//        notificationManager.notify(123, myNotificationBuilder.build())
//
//        return myNotificationBuilder
//    }

//    private fun notificationManager(): NotificationManager {
//        val notificationManager =
//            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
//            val notificationChannel =
//                NotificationChannel("123", "beep", NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        return notificationManager
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createCustomNotificationChannel(channelId: String, channelName: String) {
//        val channel =
//            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//
//        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        manager.createNotificationChannel(channel)
//    }
}

