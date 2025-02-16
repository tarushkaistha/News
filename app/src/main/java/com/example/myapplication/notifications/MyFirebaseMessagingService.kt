package com.example.myapplication.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.pushbase.MoEPushHelper


class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)


//        Log.d("service", "on token observe: $token ")
//
        MoEFireBaseHelper.getInstance().passPushToken(applicationContext, token)
//
//        Log.d("service", "on pass psh token observe: $token ")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        handleMessage(message)


    }

    private fun handleMessage(remoteMessage: RemoteMessage) {
        val handler = android.os.Handler(Looper.getMainLooper())
        handler.post {
//            remoteMessage.data.isNotEmpty().let {
//                Toast.makeText(baseContext, remoteMessage.data.toString(), Toast.LENGTH_LONG).show()
//
//            }


//            val payload = remoteMessage.data




//            Log.d("fbservice", "notification payload: $payload")
//            Log.d("fbservicecarousel", "test carousel payoad: $payload")


            val title = remoteMessage.data["title"]
            val content = remoteMessage.data["content"]

            val payload = remoteMessage.toIntent().extras

            Log.d(Utils.MOENGAGE_TAG, "check payload: $payload")

//            showNotification(title, content, pii = null)

            val bundle = Bundle()


            for ((key, value) in remoteMessage.data.entries) {
                bundle.putString(key, value)
//                MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, bundle)
            }

            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, remoteMessage.data)
//            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, payload!!)
//            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, bundle)
//            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, bundle)


//            val myAfPushLink = bundle.getString("my_key")

//            val title = remoteMessage.data[]
//            showNotification(title, body, pii)


            val intent = Intent(
                Intent.ACTION_DEFAULT,
                Uri.parse("https://mfast.vn/khong-can-bang-cap-chuyen-mon-van-kiem-duoc-20-30-trieu-moi-thang/")
            ).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }.putExtras(bundle)

            val pii = PendingIntent.getActivity(
                applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )


//            showNotification(title, body, pii)


            if (MoEPushHelper.getInstance().isFromMoEngagePlatform(remoteMessage.data)) {
                MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, remoteMessage.data)
            }
//                Log.d("myfbservice", "my push payload received: $myPushPayLoad ")
//            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, myPushPayLoad)
            try {////                MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, myPushPayLoad)
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
////                    val sentFromWhere = remoteMessage.data.containsValue("app")
////                    editor.putBoolean("youSentFrom", sentFromWhere)
////                    editor.apply()
////
////                    if (sharedPreference.getBoolean("youSentFrom", true)) {
////                        Toast.makeText(
////                            applicationContext, "Can show notification", Toast.LENGTH_LONG
////                        ).show()
//
////            showNotification(title, body, pii)
//            } else {
////                        Toast.makeText(
////                            applicationContext, "Cannot show notification", Toast.LENGTH_LONG
////                        ).show()
//
////                        MoEFireBaseHelper.getInstance()
////                            .passPushPayload(applicationContext, myPushPayLoad)
//            }
//
//                } else {
//                    Logger.print { "onMessageReceived() : Not a MoEngage Payload." }
//                }
//
//            } catch (e: Exception) {
//                Logger.print(LogLevel.ERROR, e) { "myfbservice onMessageReceived() : " }
//            }

//            showNotification("abc", "def", pii = null)



        }
    }

    private fun showNotification(
        title: String?, body: String?, pii: PendingIntent?
    ): NotificationCompat.Builder {


        val myNotificationView = RemoteViews(packageName, R.layout.add_btn_in_custom_notification)

        myNotificationView.setOnClickPendingIntent(R.id.clickButton, pii)


        val notificationManager =
            notificationManager()

        val myNotificationBuilder = NotificationCompat.Builder(
            applicationContext, "123"
        ).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle(title)
            .setContentText(body).setContentIntent(pii).setDefaults(Notification.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)/*.setCustomContentView(myNotificationView)*/

        notificationManager.notify(123, myNotificationBuilder.build())

        return myNotificationBuilder
    }

    private fun notificationManager(): NotificationManager {
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("123", "beep", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        return notificationManager
    }
}

