package com.example.myapplication.ui

//import com.example.myapplication.MyApplication.Companion.moEngage
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.Utils
import com.example.myapplication.databinding.ActivityMainBinding
import com.moengage.core.MoEngage

class MainActivity : BaseActivityCompat() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(com.moengage.core.R.layout.activity_moe_rich_landing)
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fcv.id) as NavHostFragment
        val navController = navHostFragment.navController

    }

    override fun onStart() {
        super.onStart()


        Log.d(Utils.MOENGAGE_TAG, "onStart: started callback")


//        moEngage = MoEngage.Builder(application, "Z1UDNSWJALFR3UTPWWMCSF5Z", DATA_CENTER_1)
//            .configureLogs(LogConfig(LogLevel.VERBOSE, true)).configureNotificationMetaData(
//                NotificationConfig(
//                    R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground
//                )
//            ).configureMoEngageEnvironment(MoEngageEnvironmentConfig(MoEngageEnvironment.TEST))
//            .configureFcm(FcmConfig(true)).build()
//
//
//        MoEngage.initialiseDefaultInstance(moEngage)
//
//        Handler().postDelayed({
//            MoEInAppHelper.getInstance().showInApp(this)
//        }, 2000)

//        Handler().postDelayed({}, 1000)


    }

    override fun onResume() {
        super.onResume()

        Log.d(Utils.MOENGAGE_TAG, "onresume: resumed callback")


    }

    override fun onPause() {
        super.onPause()
//        Toast.makeText(this, "main act pause", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()

//        MoEInAppHelper.getInstance().resetInAppContext()
    }

    companion object {
        lateinit var moEngage: MoEngage
    }


}