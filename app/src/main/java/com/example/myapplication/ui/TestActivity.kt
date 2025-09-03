package com.example.myapplication.ui

//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.model.environment.MoEngageEnvironment
import android.app.AlarmManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.Utils
import com.example.myapplication.databinding.TestActivityBinding
//import com.moengage.inapp.MoEInAppHelper
//import com.moengage.inapp.listeners.OnClickActionListener
//import com.moengage.inapp.model.ClickData
//import com.moengage.inapp.model.actions.NavigationAction
import com.moengage.pushbase.MoEPushHelper


class TestActivity() : AppCompatActivity() {

    private lateinit var binding: TestActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val payload: Bundle? = intent.extras
//        if (payload != null) {
//            MoEPushHelper.getInstance().logNotificationClick(applicationContext, payload)
//        }

        intent.extras.let {
            Log.d(Utils.MOENGAGE_TAG, "onCreate data: $intent")
            MoEPushHelper.getInstance().logNotificationClick(this@TestActivity, intent)
        }

//        MoECardHelper.setSyncCompleteListener(object : SyncCompleteListener {
//            override fun onSyncComplete(data: SyncCompleteData?) {
//                Log.d(Utils.MOENGAGE_TAG, "onSyncComplete for default cards : $data")
//            }
//
//        })


//        MoECardHelper.getUnClickedCardCountAsync(this, object : UnClickedCountListener {
//            override fun onCountAvailable(data: UnClickedCountData?) {
//                Log.d(Utils.MOENGAGE_TAG, data?.count.toString())
//                binding.unclickedCardsCountTv.text = "I have ${data?.count} unclicked cards"
//            }
//
//        })
//
//        MoECardHelper.getNewCardCountAsync(this, object : NewCardCountAvailableListener {
//            override fun onCountAvailable(newCardCountData: NewCardCountData?) {
//                Log.d(Utils.MOENGAGE_TAG, "on new Count available : ${newCardCountData?.count}")
//                binding.newCardsCountTv.text = "I have ${newCardCountData?.count} new cards"
//            }
//
//        })
//        binding.openDefaultCardScreenBtn.setOnClickListener {
//            startActivity(Intent(this, CardActivity::class.java))
//
//            Log.d(Utils.MOENGAGE_TAG, "i am here")
//        }


        binding.grantNotificationPermission.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val alarmManager = ContextCompat.getSystemService(this, AlarmManager::class.java)
                if (alarmManager?.canScheduleExactAlarms() == false) {
                    startActivity(Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
                } else {
                    Toast.makeText(
                        this, "Notification Permission already granted", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


    }

    override fun onStart() {
        super.onStart()

        Log.d("starttf", "on start called in tf: ")

//        MoEInAppHelper.getInstance().setClickActionListener(object : OnClickActionListener {
//            override fun onClick(clickData: ClickData): Boolean {
//                Log.d("moengage onclick", "onClick in-app data: $clickData ")
//
//                val c: NavigationAction = clickData.action as NavigationAction
//                val d = c.navigationUrl
//                Log.d("moengage onclick", "onClick click data: $d")
//                return true
//            }
//
//        })
//        MoEInAppHelper.getInstance().showInApp(this)
//        moEngage =
//            MoEngage.Builder(application, "Z1UDNSWJALFR3UTPWWMCSF5Z", DataCenter.DATA_CENTER_1)
//                .configureLogs(LogConfig(LogLevel.VERBOSE, true)).configureNotificationMetaData(
//                    NotificationConfig(
//                        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground
//                    )
//                ).configureMoEngageEnvironment(MoEngageEnvironmentConfig(MoEngageEnvironment.TEST))
//                .configureFcm(FcmConfig(true)).build()
//
//
//        MoEngage.initialiseDefaultInstance(moEngage)

//        MoEInAppHelper.getInstance().showInApp(this)
    }

    //
    override fun onStop() {
        super.onStop()

        Log.d("stoptf", "on stop called in tf: ")
    }
//
//    override fun onResume() {
//        super.onResume()
//        MoEInAppHelper.getInstance().showInApp(this)
//        MoECardHelper.getUnClickedCardCountAsync(this, object : UnClickedCountListener {
//            override fun onCountAvailable(data: UnClickedCountData?) {
//                Log.d(Utils.MOENGAGE_TAG, data?.count.toString())
//                binding.unclickedCardsCountTv.text = "I have ${data?.count} unclicked cards"
//            }
//
//        })
//
//
//        MoECardHelper.getNewCardCountAsync(this, object : NewCardCountAvailableListener {
//            override fun onCountAvailable(newCardCountData: NewCardCountData?) {
//                Log.d(
//                    Utils.MOENGAGE_TAG,
//                    "on new Count available on resume  : ${newCardCountData?.count}"
//                )
//                binding.newCardsCountTv.text = "I have ${newCardCountData?.count} new cards"
//            }
//
//        })
//    }

    override fun onPause() {
        super.onPause()

        Log.d("pausetf", "on pause called in tf: ")
    }

    override fun onResume() {
        super.onResume()

        Log.d("resumetf", "on resume called in tf: ")
    }

//    companion object {
//        lateinit var moEngage: MoEngage
//    }


}