package com.example.myapplication

import android.app.Application
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.CustomPushMessageListener
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.MoEngageEnvironmentConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.model.environment.MoEngageEnvironment
import com.moengage.pushbase.MoEPushHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val moEngage = MoEngage.Builder(this, "Z1UDNSWJALFR3UTPWWMCSF5Z", DataCenter.DATA_CENTER_1)
            .configureLogs(LogConfig(LogLevel.VERBOSE, true)).configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_background
                )
        )

            .configureMoEngageEnvironment(MoEngageEnvironmentConfig(MoEngageEnvironment.TEST))
        .configureFcm(FcmConfig(false)).build()

        MoEngage.initialiseDefaultInstance(moEngage)


//        MoEPushHelper.getInstance().registerMessageListener(CustomPushMessageListener())

//        val pref = getSharedPreferences("default", 0);
//        if(!pref.contains("version")){
//            // install
//            pref.edit().putInt("version", BuildConfig.VERSION_CODE).apply()
//            MoEAnalyticsHelper.setAppStatus(this, AppStatus.INSTALL)
//        } else if(pref.getInt("version", 0) != BuildConfig.VERSION_CODE) { // pref contains a version
//            MoEAnalyticsHelper.setAppStatus(this, AppStatus.UPDATE)
//            pref.edit().putInt("version", BuildConfig.VERSION_CODE).apply()
//        }


    }
}