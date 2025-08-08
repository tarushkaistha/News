package com.example.myapplication

//import com.example.myapplication.ui.CustomPushMessageListener
//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.model.environment.MoEngageEnvironment
//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.model.environment.MoEngageEnvironment
//import com.moengage.geofence.MoEGeofenceHelper
//import com.moengage.geofence.listener.OnGeofenceHitListener
//import com.moengage.geofence.model.GeofenceData
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleObserver
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.DataSyncConfig
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
//import com.moengage.core.config.MoEngageEnvironmentConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.disableIntegrationValidator
//import com.moengage.core.model.environment.MoEngageEnvironment
import com.moengage.pushbase.MoEPushHelper
import kotlinx.coroutines.DelicateCoroutinesApi

//import com.moengage.core.model.environment.MoEngageEnvironment


class MyApplication : Application(), LifecycleObserver {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()


        initMoEngage()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initMoEngage() {
        val moEngage: MoEngage = MoEngage.Builder(this, "Z1UDNSWJALFR3UTPWWMCSF5Z",DataCenter.DATA_CENTER_1)
            .configureLogs(LogConfig(LogLevel.VERBOSE, true)).configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                )
            )
//            .configureMoEngageEnvironment(MoEngageEnvironmentConfig(MoEngageEnvironment.LIVE))
//            .configureFcm(FcmConfig(false))

            .build()


        MoEngage.initialiseDefaultInstance(moEngage)

//        disableIntegrationValidator()

//        createCustomNotificationChannel("General Notification","General Notification")

//        MoEPushHelper.getInstance().registerMessageListener(CustomPushMessageListener())

//        MoEGeofenceHelper.getInstance().addListener(object : OnGeofenceHitListener {
//            override fun geofenceHit(geofenceData: GeofenceData): Boolean {
//                Log.d("mygeofence", "geofenceHit data: $geofenceData")
//
//                val geofencingEvent = GeofencingEvent.fromIntent(geofenceData.intent)
//                val triggeredFences = geofencingEvent!!.triggeringGeofences
//
//                Log.d("my geofence", "geofenceHit triggered fences: $triggeredFences")
//
//                val transitionType = geofencingEvent.geofenceTransition
//
//                Log.d("my geofence", "geofenceHit transition type: $transitionType")
//
//                return false
//            }
//
//        })


    }


//    companion object {
//        lateinit var moEngage: MoEngage
//    }


//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createCustomNotificationChannel(channelId: String, channelName: String) {
//        val channel =
//            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//
//        val soundUri: Uri =
//            Uri.parse("android.resource://" + packageName + "/" + R.raw.livechat_notify)
//
//
//        Log.d("my sound uri", "sound uri: $soundUri")
//
//        val audioAttributes =
//            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                .setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
//        channel.setSound(soundUri, audioAttributes)
//        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        manager.createNotificationChannel(channel)
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createCustomNotificationChannel(channelId: String, channelName: String) {
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}