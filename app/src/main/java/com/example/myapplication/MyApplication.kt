package com.example.myapplication

//import com.example.myapplication.ui.CustomPushMessageListener
//import com.moengage.core.config.MoEngageEnvironmentConfig
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
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.config.StorageEncryptionConfig
import com.moengage.core.config.StorageSecurityConfig
import kotlinx.coroutines.DelicateCoroutinesApi

//import com.moengage.core.model.environment.MoEngageEnvironment


class MyApplication : Application(), LifecycleObserver {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()


        initMoEngage()

    }

    fun initMoEngage() {
        val moEngage = MoEngage.Builder(this, "Z1UDNSWJALFR3UTPWWMCSF5Z")
            .setDataCenter(DataCenter.DATA_CENTER_1)
            .configureLogs(LogConfig(LogLevel.VERBOSE, true)).configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    notificationColor = R.color.moe_rich_push_progress_bar_background_color,
                    true,
                    false,
                    false
                )
            )
//            .configureMoEngageEnvironment(MoEngageEnvironmentConfig(MoEngageEnvironment.TEST))
            .configureFcm(FcmConfig(true)).build()


        MoEngage.initialiseDefaultInstance(moEngage)


    }


//    companion object {
//        lateinit var moEngage: MoEngage
//    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createCustomNotificationChannel(channelId: String, channelName: String) {
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

        val soundUri: Uri =
            Uri.parse("android.resource://" + packageName + "/" + R.raw.livechat_notify)


        Log.d("my sound uri", "sound uri: $soundUri")

        val audioAttributes =
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
        channel.setSound(soundUri, audioAttributes)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}