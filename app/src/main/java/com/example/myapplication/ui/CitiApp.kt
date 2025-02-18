package com.example.myapplication.ui

import android.app.Application
import com.example.myapplication.R
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NetworkAuthorizationConfig
import com.moengage.core.config.NetworkDataSecurityConfig
import com.moengage.core.config.NetworkRequestConfig
import com.moengage.core.config.NotificationConfig

class CitiApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val moEngage = MoEngage.Builder(this, "Z1UDNSWJALFR3UTPWWMCSF5Z")
            .setDataCenter(DataCenter.DATA_CENTER_1)
            .configureLogs(LogConfig(LogLevel.VERBOSE, true)).configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.ic_launcher_foreground,
                    R.drawable.ic_launcher_foreground,
                    notificationColor = R.color.moe_rich_push_progress_bar_background_color,
                    false,
                    false,
                    false
                )
            ).configureNetworkRequest(
                NetworkRequestConfig(
                    NetworkDataSecurityConfig(
                        true,
                        encryptionEncodedDebugKey = "eyJrZXkiOiJVdGdndkthMnNIMmQyd3RQLytaRDlhM3RqUG9VbEJUcGZjSk90UnRCTHQ0PSIsInZlcnNpb24iOiJWMSJ9",
                        encryptionEncodedReleaseKey = "eyJrZXkiOiJoZnA1YnFFZGJETzdnWVQ5THRPS0Zsb29UeC8rWTVPNFJUMVRLWENaNEpnPSIsInZlcnNpb24iOiJWMSJ9"
                    ),
                    NetworkAuthorizationConfig(true)
                    )
            )
//            .configureMoEngageEnvironment(MoEngageEnvironmentConfig(MoEngageEnvironment.TEST))
            .configureFcm(FcmConfig(true)).build()


        MoEngage.initialiseDefaultInstance(moEngage)
    }

}