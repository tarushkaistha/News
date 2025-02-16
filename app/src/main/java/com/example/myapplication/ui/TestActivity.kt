package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.TestActivityBinding
import com.moengage.core.DataCenter
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
//import com.moengage.core.config.MoEngageEnvironmentConfig
import com.moengage.core.config.NotificationConfig
//import com.moengage.core.model.environment.MoEngageEnvironment
import com.moengage.inapp.MoEInAppHelper


class TestActivity() : AppCompatActivity() {

    private lateinit var binding: TestActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("testact", "onCreate my data ${intent.data}: ")


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


//        binding.grantNotificationPermission.setOnClickListener {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                val alarmManager = ContextCompat.getSystemService(this, AlarmManager::class.java)
//                if (alarmManager?.canScheduleExactAlarms() == false) {
//                    startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
//                } else {
//                    Toast.makeText(
//                        this, "Notification Permission already granted", Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }


    }

    override fun onStart() {
        super.onStart()

        Log.d("starttf", "on start called in tf: ")
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