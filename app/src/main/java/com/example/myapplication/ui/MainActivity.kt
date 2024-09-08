package com.example.myapplication.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.databinding.ActivityMainBinding

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

//        Toast.makeText(this, "main act start", Toast.LENGTH_LONG).show()

//        MoEInAppHelper.getInstance().showInApp(this)
    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(this, "main act resume", Toast.LENGTH_LONG).show()
//        MoEInAppHelper.getInstance().showInApp(this)
    }

    override fun onPause() {
        super.onPause()
//        Toast.makeText(this, "main act pause", Toast.LENGTH_LONG).show()
    }

    companion object {
        val mainActTag = "MAIN_ACTIVITY"

    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        Log.d(Utils.MOENGAGE_TAG, "main activity comes in on config change function: ")
//        MoEInAppHelper.getInstance().onConfigurationChanged()
//    }

}