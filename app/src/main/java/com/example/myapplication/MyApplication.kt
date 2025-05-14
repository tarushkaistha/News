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
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.collection.arraySetOf
import androidx.lifecycle.LifecycleObserver
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.config.ScreenNameTrackingConfig
import com.moengage.core.config.TrackingOptOutConfig
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
            .configureTrackingOptOut(
                TrackingOptOutConfig(
                    false, false, arraySetOf(),
                    ScreenNameTrackingConfig(true, setOf())
                )
            )
            .build()


        MoEngage.initialiseDefaultInstance(moEngage)


    }
}