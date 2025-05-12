package com.example.myapplication

//import com.example.myapplication.ui.CustomPushMessageListener
//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.model.environment.MoEngageEnvironment
//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.model.environment.MoEngageEnvironment
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleObserver
import com.example.myapplication.ui.CustomPushMessageListener
import com.google.android.gms.location.GeofencingEvent
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.pushbase.MoEPushHelper
//import com.moengage.geofence.MoEGeofenceHelper
//import com.moengage.geofence.listener.OnGeofenceHitListener
//import com.moengage.geofence.model.GeofenceData
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
        val moEngage = MoEngage.Builder(this, "Z1UDNSWJALFR3UTPWWMCSF5Z")
            .setDataCenter(DataCenter.DATA_CENTER_1)
            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
            .configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                )
            )
            .configureFcm(FcmConfig(false))
            .build()


        MoEngage.initialiseDefaultInstance(moEngage)


    }
}