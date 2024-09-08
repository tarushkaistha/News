package com.example.myapplication.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.google.firebase.messaging.FirebaseMessagingService

object MyNotificationBuilder {
    fun showMyNotification(
        context: Context, title: String?, body: String?, pii: PendingIntent?
    ): NotificationCompat.Builder {
        val myNotificationBuilder = NotificationCompat.Builder(
            context, "123"
        ).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title)
            .setContentText(body).setContentIntent(pii)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            context.getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("123", "beep", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }


        notificationManager.notify(123, myNotificationBuilder.build())

        return myNotificationBuilder
    }

}