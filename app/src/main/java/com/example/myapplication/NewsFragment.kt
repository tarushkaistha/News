package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.NewsFragmentBinding
import com.example.myapplication.ui.CustomInboxActivity
import com.moengage.inbox.ui.view.InboxActivity

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

        binding.defMoeInbox.setOnClickListener {
            val intent = Intent(requireActivity(), InboxActivity::class.java)
            startActivity(intent)


        }

        binding.myCustomInbox.setOnClickListener {
//            findNavController().navigate(R.id.action_newsFragment_to_customInbox)
            startActivity(Intent(requireActivity(), CustomInboxActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
    }

}