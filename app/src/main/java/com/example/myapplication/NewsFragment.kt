package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.NewsFragmentBinding
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.enableAdIdTracking
import com.moengage.geofence.MoEGeofenceHelper
import com.moengage.inapp.MoEInAppHelper
import org.json.JSONArray
import org.json.JSONObject

class NewsFragment : Fragment() {

    private lateinit var binding: NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = NewsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enableAdIdTracking(requireActivity())

        binding.loginBtn.setOnClickListener {
//            MoEAnalyticsHelper.setUniqueId(requireActivity(), "testtarush")

            MoEAnalyticsHelper.setAlias(requireActivity(),"my alias two")
        }

        binding.logoutBtn.setOnClickListener {
//            MoECoreHelper.logoutUser(requireActivity())

            findNavController().navigate(R.id.action_newsFragment_to_articlesFragment)

        }

        binding.trackCustomEvent.setOnClickListener {

            val mapOfCountry = hashMapOf<String, String>()
//
//            mapOfCountry["USA"] = "Charlotte"
//            mapOfCountry["India"] = "Bangalore"
//
//            println("my map of country : $mapOfCountry")
//
//            val arrayMapOfCountry = arrayListOf(mapOfCountry)

            val intArray = JSONArray()
            intArray.put(1)
            intArray.put(2)

//            println("my array map of country : ${arrayMapOfCountry[0]}")

            val obj = JSONObject()
            obj.put("usa", "charlotte")
            obj.put("india", "chandigarh")


            val arrayOfCountry = JSONArray()
            arrayOfCountry.put(obj)

            val property = Properties()
            property.addAttribute("country", arrayOfCountry.toString())
            MoEAnalyticsHelper.trackEvent(requireActivity(), "Country_Event", property)
        }

        MoEGeofenceHelper.getInstance().startGeofenceMonitoring(requireActivity())


    }

    override fun onResume() {
        super.onResume()
//        MoEInAppHelper.getInstance().showInApp(requireActivity())
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


        MoEInAppHelper.getInstance().setInAppContext(setOf("news"))
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