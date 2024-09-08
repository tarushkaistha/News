package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.AbcBinding

class Abc() : AppCompatActivity() {


    private lateinit var binding: AbcBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = AbcBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.webView.settings.javaScriptEnabled = true

        binding.webView.loadUrl("https://moxa.onelink.me/VqhM/wpb85ifc?moe_app_id=2E3F8NY021BHG7BNCHCYWH9H&af_push_link=https%3A%2F%2Fmoxa.onelink.me%2FVqhM%2Fwpb85ifc%20%0D%0A%0A&gcm_notificationType=gcm_webNotification&gcm_image_url=https%3A%2F%2Fimage.moengage.com%2Fmoxamoengage%2F20240606020748786750OJU2SCPROMOJUARAPPOB1042x429jpgcompmoxamoengage.jpg&moe_cid_attr=%7B%22moe_campaign_name%22%3A%22Update%20App%20-%20PPOB%20-%2006%5C%2F06%5C%2F2024%22%2C%22moe_campaign_type%22%3A%22General%20Push%20Campaign%22%2C%22moe_campaign_tags%22%3A%5B%22all%20users%22%2C%22promotional%22%5D%2C%22sent_epoch_time%22%3A1717642777%2C%22moe_campaign_id%22%3A%226661212b1ac9a020aaf66f21%22%2C%22moe_campaign_channel%22%3A%22Push%22%2C%22moe_delivery_type%22%3A%22One%20Time%22%7D&push_from=moengage&moe_isDefaultAction=true&inbox_expiry=1720234777&MOE_MSG_RECEIVED_TIME=1717642781323&gcm_alert=Update%20app%20ke%20versi%20terbaru%20buat%20dapetin%20Diskon%20s%2Fd%20125%20RB%20untuk%20SEMUA%20TAGIHAN%20di%20Moxa%F0%9F%8E%89&gcm_title=Emang%20Boleh%20Bayar%20Tagihan%20Sehemat%20Ini%3F%E2%9C%A8&moe_push_service=fcm&gcm_webUrl=https%3A%2F%2Fmoxa.onelink.me%2FVqhM%2Fwpb85ifc&gcm_campaign_id=6661212b1ac9a020aaf66f21_F_T_GP_AB_1_P_0_L_0&moe_channel_id=moe_default_channel")
//        val extras = intent.getStringExtra("pushLink")
//        if (extras?.isNotEmpty() == true) {
//            Log.d(Utils.MOENGAGE_TAG, "onCreate has my link: $extras")
//
//            binding.webView.loadUrl(extras)
//            binding.webView.settings.javaScriptEnabled = true
//            binding.webView.settings.builtInZoomControls = true
//            binding.webView.webViewClient = object : WebViewClient() {
//                @Deprecated("Deprecated in Java")
//                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                    view?.loadUrl(url!!)
//                    return true
//                }
//            }
//
//
//        } else {
//            Toast.makeText(this, "Wrong url", Toast.LENGTH_SHORT).show()
//        }

    }


}