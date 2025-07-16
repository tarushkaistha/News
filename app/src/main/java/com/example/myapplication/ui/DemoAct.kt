package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDemoBinding
import com.moengage.inapp.MoEInAppHelper

class DemoAct : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

//        MoEInAppHelper.getInstance().setInAppContext(setOf("hey","hellos"))
        MoEInAppHelper.getInstance().showNudge(this)
    }

    override fun onStop() {
        super.onStop()

//        MoEInAppHelper.getInstance().resetInAppContext()
    }

    override fun onPause() {
        super.onPause()

//        MoEInAppHelper.getInstance().resetInAppContext()
    }
}