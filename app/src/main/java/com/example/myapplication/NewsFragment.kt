package com.example.myapplication

//import com.moengage.geofence.MoEGeofenceHelper
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.NewsFragmentBinding
import com.moengage.cards.ui.CardActivity
import com.moengage.core.MoECoreHelper
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.disableSdk
import com.moengage.core.enableAdIdTracking
import com.moengage.geofence.MoEGeofenceHelper
import com.moengage.inapp.MoEInAppHelper

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

//        startActivity(Intent(ACTION_REQUEST_SCHEDULE_EXACT_ALARM))

        binding.loginBtn.setOnClickListener {
//            MoEAnalyticsHelper.setUniqueId(requireActivity(), "UID7")

            MoEAnalyticsHelper.setAlias(requireActivity(), "my alias three")


        }

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

            val property = Properties()
//            property.addAttribute("country", arrayOfCountry.toString())
//            MoEAnalyticsHelper.trackEvent(requireActivity(), "Country_Event", property)

//            property.addAttribute("country","india")
//
//            MoEAnalyticsHelper.trackEvent(requireActivity(), "in-app-shown", property)

//            MoEAnalyticsHelper.setBirthDate(requireActivity(),"2000-09-27")

            startActivity(Intent(requireActivity(), CardActivity::class.java))


        }

        MoEGeofenceHelper.getInstance().startGeofenceMonitoring(requireActivity())

//        MoEPushHelper.getInstance().requestPushPermission(requireActivity())


    }

    override fun onResume() {
        super.onResume()
//        MoEInAppHelper.getInstance().showNudge(requireActivity())
//        MoEInAppHelper.getInstance().setInAppContext(setOf("news"))
//        MoEInAppHelper.getInstance().setClickActionListener(object : OnClickActionListener {
//            override fun onClick(clickData: ClickData): Boolean {
//                Log.d("moengage onclick", "onClick in-app data: $clickData ")
//
//
//
//                return false
//            }
//
//        })


//        MoEInAppHelper.getInstance().setInAppContext(setOf("news"))
        MoEInAppHelper.getInstance().showInApp(requireActivity())
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