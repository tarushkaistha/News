package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.NewsFragmentBinding
import com.moengage.pushbase.MoEPushHelper

class NewsFragment : Fragment() {

    private lateinit var binding: NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = NewsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MoEPushHelper.getInstance().requestPushPermission(requireActivity())

    }


    override fun onResume() {
        super.onResume()
    }

}