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
//import com.moe.pushlibrary.MoEHelper
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.NewsFragmentBinding
import com.example.myapplication.ui.CustomWebView
import com.example.myapplication.ui.TestActivity
import com.moengage.core.MoECoreHelper
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.enableAdIdTracking
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inapp.listeners.SelfHandledCampaignsAvailableListener
import com.moengage.inapp.model.SelfHandledCampaignsData
import com.moengage.pushbase.MoEPushHelper


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


        MoEPushHelper.getInstance().requestPushPermission(requireActivity())

//        MoEAnalyticsHelper.setAppStatus(requireActivity(), AppStatus.UPDATE)
        binding.customWebView.setOnClickListener {
            startActivity(Intent(requireActivity(), CustomWebView::class.java))
        }

        binding.testFrg.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_testFragment)
        }

        binding.demoAct.setOnClickListener {
//            startActivity(Intent(requireActivity(), DemoAct::class.java))
            startActivity(Intent(requireActivity(), TestActivity::class.java))

//            MoEAnalyticsHelper.trackUserPushPreference(requireActivity(),false)
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


            MoEAnalyticsHelper.identifyUser(requireActivity(), "faang")

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

        binding.logoutBtn.setOnClickListener {
            MoECoreHelper.logoutUser(requireActivity())

//            findNavController().navigate(R.id.action_newsFragment_to_articlesFragment)

        }

        binding.trackCustomEvent.setOnClickListener {


        }

    }

    override fun onResume() {
        super.onResume()

        Log.d(Utils.MOENGAGE_TAG, "news fragment onresume: resumed callback")
//        MoEInAppHelper.getInstance().setInAppContext(setOf("myeyes"))
//        MoEInAppHelper.getInstance().showInApp(requireActivity())
//        MoEInAppHelper.getInstance().showNudge(requireActivity())


//        MoEInAppHelper.getInstance().setClickActionListener(object : OnClickActionListener {
//            override fun onClick(clickData: ClickData): Boolean {
//                Log.d("moengage onclick", "onClick in-app data: $clickData ")
//
//                val c: NavigationAction = clickData.action as NavigationAction
//                val d = c.navigationUrl
//                Log.d("moengage onclick", "onClick click data: $d")
//                return true
//            }
//
//        })

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


        MoEInAppHelper.getInstance().getSelfHandledInApps(
            requireActivity(),
            object : SelfHandledCampaignsAvailableListener {
                override fun onCampaignsAvailable(campaigns: SelfHandledCampaignsData?) {
                    Log.d(Utils.MOENGAGE_TAG, "multi sh in-app: $campaigns")
                }

            })

    }

    private fun setMoEngageUserParams(context: Context?, params: Map<String, Any>) {
        context ?: return
        params.forEach {
            MoEAnalyticsHelper.setUserAttribute(
                context, attributeName = it.key, attributeValue = it.value
            )
        }
    }

    override fun onStop() {
        super.onStop()

        Log.d(Utils.MOENGAGE_TAG, "news fragemnt onstop: stop callback")
//        MoEInAppHelper.getInstance().resetInAppContext()
    }

    override fun onPause() {
        super.onPause()
        Log.d(Utils.MOENGAGE_TAG, "news fragment onpause: paused callback")
        MoEInAppHelper.getInstance().resetInAppContext()
    }

}