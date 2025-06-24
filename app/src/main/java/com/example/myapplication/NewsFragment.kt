package com.example.myapplication

//import com.moengage.geofence.MoEGeofenceHelper
//import com.moengage.cards.ui.CardActivity
//import com.moengage.geofence.MoEGeofenceHelper
//import com.moengage.inapp.MoEInAppHelper
//import com.moengage.inapp.listeners.OnClickActionListener
//import com.moengage.inapp.model.ClickData
//import com.moengage.inapp.model.actions.NavigationAction
//import com.moe.pushlibrary.MoEHelper
//import com.moengage.inapp.MoEInAppHelper
//import com.moengage.inapp.listeners.SelfHandledAvailableListener
//import com.moengage.inapp.model.SelfHandledCampaignData
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.NewsFragmentBinding
import com.example.myapplication.ui.CustomWebView
import com.example.myapplication.ui.DemoAct
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.MoECoreHelper
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.enableAdIdTracking
import com.moengage.core.model.AppStatus
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inapp.listeners.OnClickActionListener
import com.moengage.inapp.model.ClickData
import com.moengage.inapp.model.actions.NavigationAction
import org.json.JSONArray
import org.json.JSONObject
import java.util.Date


class NewsFragment : Fragment() {

    private lateinit var binding: NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = NewsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enableAdIdTracking(requireActivity())

//        MoEPushHelper.getInstance().requestPushPermission(requireActivity())

        binding.customWebView.setOnClickListener {
            startActivity(Intent(requireActivity(), CustomWebView::class.java))
        }

        binding.demoAct.setOnClickListener {
            startActivity(Intent(requireActivity(), DemoAct::class.java))
        }

//        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.d(
//                    "NewsFragment", "Fetching FCM registration token failed", task.exception
//                )
//                return@addOnCompleteListener
//            }
//
//            val token = task.result
//            Log.d("NewsFragment", "Resultant token: $token")
//            MoEFireBaseHelper.getInstance().passPushToken(requireActivity(), token)
//            Toast.makeText(requireActivity(), "Resultant token: $token", Toast.LENGTH_SHORT).show()
//
//        }
//        startActivity(Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM))

        binding.loginBtn.setOnClickListener {

            MoEHelper.getInstance(requireActivity()).setUniqueId("chd1")
//            MoEAnalyticsHelper.setUniqueId(requireActivity(), "delhi1")

//            MoEAnalyticsHelper.identifyUser(requireActivity(),"terminal8a")

//            lifecycleScope.launch(Dispatchers.IO) {
//                val c = MoEAnalyticsHelper.getUserIdentities(
//                    requireActivity(), "Z1UDNSWJALFR3UTPWWMCSF5Z"
//                )
//                if (c != null) {
//                    Log.d(Utils.MOENGAGE_TAG, "get user identity: ${c.entries}")
//                }
//            }

//            MoEAnalyticsHelper.setEmailId(requireActivity(),"tarush@yahoo.com")
        }

//        MoEAnalyticsHelper.setUniqueId(requireActivity(), "UID 10")

        binding.logoutBtn.setOnClickListener {
            MoECoreHelper.logoutUser(requireActivity())

//            findNavController().navigate(R.id.action_newsFragment_to_articlesFragment)

        }

        binding.trackCustomEvent.setOnClickListener {

//            MoEPushHelper.getInstance().pushPermissionResponse(requireActivity(), false)

            val mapOfCountry = hashMapOf<String, String>()
//
//            mapOfCountry["USA"] = "Charlotte"
//            mapOfCountry["India"] = "Bangalore"
//
//            println("my map of country : $mapOfCountry")
//
//            val arrayMapOfCountry = arrayListOf(mapOfCountry)

//            val intArray = JSONArray()
//            intArray.put(1)
//            intArray.put(2)
//
////            println("my array map of country : ${arrayMapOfCountry[0]}")
//
//            val obj = JSONObject()
//            obj.put("usa", "charlotte")
//            obj.put("india", "chandigarh")
//
//
//            val arrayOfCountry = JSONArray()
//            arrayOfCountry.put(obj)

//            val jsonObject = JSONObject().put("kaistha", JSONArray(listOf("tony", "iron")))
            val jsonObject = JSONObject().apply {
                put("packagesAvailable", 7)
                put("countryName", "global")
            }
            val property = Properties()
//            property.addAttribute(jsonObject.toString(), jsonObject.toString())
//            MoEAnalyticsHelper.trackEvent(requireActivity(), "BSDK", property)

//            property.addAttribute("attributeDate", Date())
//            property.addDateIso("attributeDateIso", "2022-02-10T21:12:00Z")
            property.addDateIso("attributeDateIsoTomorrow", "2025-06-19T21:12:00Z")
            property.addAttribute("myDate", Date())

            MoEAnalyticsHelper.trackEvent(requireActivity(), "date_event", property)


        }

//        MoEPushHelper.getInstance().requestPushPermission(requireActivity())

    }

    override fun onResume() {
        super.onResume()

        Log.d(Utils.MOENGAGE_TAG, "fragment onresume: resumed callback")
//        MoEInAppHelper.getInstance().setInAppContext(setOf("hey,hellos"))
//        MoEInAppHelper.getInstance().setInAppContext(setOf("hey","hellos"))
        MoEInAppHelper.getInstance().showInApp(requireActivity())
//        MoEInAppHelper.getInstance().showNudge(requireActivity())

        MoEInAppHelper.getInstance().setClickActionListener(object : OnClickActionListener {
            override fun onClick(clickData: ClickData): Boolean {
                Log.d("moengage onclick", "onClick in-app data: $clickData ")

                val c: NavigationAction = clickData.action as NavigationAction
                val d = c.navigationUrl
                Log.d("moengage onclick", "onClick click data: $d")
                return false
            }

        })

//        MoEInAppHelper.getInstance()
//            .getSelfHandledInApp(requireActivity(), object : SelfHandledAvailableListener {
//                override fun onSelfHandledAvailable(data: SelfHandledCampaignData?) {
//                    val myPayload = data?.campaign?.payload
//                    Log.d(Utils.MOENGAGE_TAG, "my sh in-app: $myPayload")
//
////                    MoEInAppHelper.getInstance().selfHandledShown(requireActivity(), data!!)
//
//                }
//
//            })


//        MoEInAppHelper.getInstance().getSelfHandledInApps(requireActivity(), object : SelfHandledCampaignsAvailableListener{
//            override fun onCampaignsAvailable(campaigns: SelfHandledCampaignsData?) {
//                Log.d(Utils.MOENGAGE_TAG, "multi sh in-app: $campaigns")
//            }
//
//        })

    }


    override fun onStop() {
        super.onStop()

        Log.d(Utils.MOENGAGE_TAG, "fragemnt onstop: stop callback")
        MoEInAppHelper.getInstance().resetInAppContext()
    }

    override fun onPause() {
        super.onPause()
        Log.d(Utils.MOENGAGE_TAG, "fragment onpause: paused callback")
//        MoEInAppHelper.getInstance().resetInAppContext()
    }

}