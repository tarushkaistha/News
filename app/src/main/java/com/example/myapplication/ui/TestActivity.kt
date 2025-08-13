package com.example.myapplication.ui

//import com.moengage.core.config.MoEngageEnvironmentConfig
//import com.moengage.core.model.environment.MoEngageEnvironment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.TestActivityBinding
import com.moengage.cards.ui.CardActivity


class TestActivity() : AppCompatActivity() {

    private lateinit var binding: TestActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openDefaultCardScreenBtn.setOnClickListener {
            startActivity(Intent(this, CardActivity::class.java))

        }


    }

    override fun onStart() {
        super.onStart()

        Log.d("starttf", "on start called in tf: ")
    }

    //
    override fun onStop() {
        super.onStop()

        Log.d("stoptf", "on stop called in tf: ")
    }

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