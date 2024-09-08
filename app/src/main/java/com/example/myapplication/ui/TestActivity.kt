package com.example.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Utils
import com.example.myapplication.databinding.TestActivityBinding
import com.moengage.core.LogLevel
import com.moengage.core.internal.global.GlobalCache
import com.moengage.core.internal.logger.Logger
import com.moengage.core.internal.rest.SCHEME_HTTP
import com.moengage.core.internal.rest.SCHEME_HTTPS


class TestActivity() : AppCompatActivity() {

    private lateinit var binding: TestActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val webView = binding.myWebView
            webView.loadUrl("https://shop.unifi.com.my/slof/register-interest?utm_source=myunifi&utm_medium=myunifi&utm_campaign=unifi_202404_hebat&utm_content=3months_waiver")
            webView.settings.javaScriptEnabled = GlobalCache.jsConfig.isJavaScriptEnabled
            webView.settings.builtInZoomControls = true
            webView.webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    try {
                        Logger.print { "${Utils.MOENGAGE_TAG} shouldOverrideUrlLoading() : Url: $url" }
                        val uri = Uri.parse(url)
                        val scheme = uri.scheme
                        if ((SCHEME_HTTP == scheme || SCHEME_HTTPS == scheme)) {
                            return false
                        }
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                        return true
                    } catch (t: Throwable) {
                        Logger.print(
                            LogLevel.ERROR, t
                        ) { "${Utils.MOENGAGE_TAG} shouldOverrideUrlLoading() : " }
                    }
                    return false
                }
            }
        } catch (t: Throwable) {
            Log.d(Utils.MOENGAGE_TAG, "onCreate:  ")
            Log.d(Utils.MOENGAGE_TAG, "onCreate: could not load web view ")
            finish()
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

//    override fun onStart() {
//        super.onStart()
//        MoEInAppHelper.getInstance().setInAppContext(setOf("context 1"))
//    }
//
//    override fun onStop() {
//        super.onStop()
//        MoEInAppHelper.getInstance().resetInAppContext()
//    }
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


}