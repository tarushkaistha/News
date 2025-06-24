package com.example.myapplication.ui

//import com.example.myapplication.MyApplication.Companion.moEngage
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.databinding.ActivityMainBinding
import com.moengage.core.MoEngage
import com.moengage.inapp.MoEInAppHelper

class MainActivity : BaseActivityCompat() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(com.moengage.core.R.layout.activity_moe_rich_landing)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.colorPrimary)
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
//        MoEInAppHelper.getInstance().showNudge(this)
//        MoEInAppHelper.getInstance().setInAppContext(setOf("news"))
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

    }

    companion object {
        lateinit var moEngage: MoEngage
    }


}