package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.Utils
import com.example.myapplication.databinding.FragmentTestBinding
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inapp.listeners.OnClickActionListener
import com.moengage.inapp.model.ClickData
import com.moengage.inapp.model.actions.NavigationAction

class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTestBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

//        MoEInAppHelper.getInstance().setInAppContext(setOf("new", "shel"))
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
    }

    override fun onStop() {
        super.onStop()

        Log.d(Utils.MOENGAGE_TAG, "test fragemnt onstop: stop callback")
        MoEInAppHelper.getInstance().resetInAppContext()
    }

    override fun onPause() {
        super.onPause()
        Log.d(Utils.MOENGAGE_TAG, "test fragment onpause: paused callback")
        MoEInAppHelper.getInstance().resetInAppContext()
    }
}