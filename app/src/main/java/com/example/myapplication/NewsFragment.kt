package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.NewsFragmentBinding
import com.moengage.cards.core.MoECardHelper
import com.moengage.cards.core.listener.CardAvailableListener
import com.moengage.cards.core.listener.SyncCompleteListener
import com.moengage.cards.core.model.CardData
import com.moengage.cards.core.model.SyncCompleteData
import com.moengage.core.MoECoreHelper
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.enableAdIdTracking
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

        MoECardHelper.onCardSectionLoaded(requireActivity(), object : SyncCompleteListener {
            override fun onSyncComplete(data: SyncCompleteData?) {

                Log.d(Utils.MOENGAGE_TAG, "onSyncComplete data: $data")

            }

        });

        binding.loginBtn.setOnClickListener {
            MoEAnalyticsHelper.setUniqueId(requireActivity(), "testsean")
        }

        binding.logoutBtn.setOnClickListener {
//            MoECoreHelper.logoutUser(requireActivity())

            MoECardHelper.fetchCards(requireActivity(), object : CardAvailableListener {
                override fun onCardAvailable(cardData: CardData?) {
                    Log.d(Utils.MOENGAGE_TAG, "onCardAvailable my card data: ${cardData!!.cards}")
                }

            })
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


    }

    override fun onResume() {
        super.onResume()
    }

}