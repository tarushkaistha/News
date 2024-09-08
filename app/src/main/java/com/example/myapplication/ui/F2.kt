package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.moengage.inapp.MoEInAppHelper

class F2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_f2, container, false)
    }

    override fun onStart() {
        super.onStart()

        MoEInAppHelper.getInstance().setInAppContext(setOf("context 2"))
    }

    override fun onResume() {
        super.onResume()
        MoEInAppHelper.getInstance().showInApp(requireActivity())
    }

    override fun onStop() {
        super.onStop()
        MoEInAppHelper.getInstance().resetInAppContext()
    }
}