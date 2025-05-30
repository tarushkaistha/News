package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Utils
import com.example.myapplication.databinding.ActivityCustomWebViewBinding
import com.moengage.core.internal.global.GlobalCache
import com.moengage.inapp.MoEInAppHelper

class CustomWebView : AppCompatActivity() {
    private lateinit var binding: ActivityCustomWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var articleUrl = String()
        try {
            articleUrl = intent.getStringExtra("url").toString()
//            articleUrl = intent.getStringExtra("url").toString()
            Log.d("cwv", "my article url: $articleUrl")
            val webView: WebView = binding.myWebView
            webView.settings.javaScriptEnabled = GlobalCache.jsConfig.isJavaScriptEnabled
            webView.settings.builtInZoomControls = true
            webView.settings.domStorageEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl(articleUrl)
//            webView.loadUrl("https://www.prexpe.com.pe/pagos-de-servicios")
        } catch (t: Throwable) {
            Log.d(Utils.MOENGAGE_TAG, "onCreate:  ")
            Log.d(Utils.MOENGAGE_TAG, "onCreate: could not load web view ")
            finish()
        }


    }

    override fun onResume() {
        super.onResume()

        MoEInAppHelper.getInstance().setInAppContext(setOf("news"))
//        MoEInAppHelper.getInstance().showInApp(this)
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