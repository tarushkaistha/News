package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.NewsFragmentBinding
import com.moengage.inbox.core.MoEInboxHelper
import com.moengage.inbox.ui.view.InboxActivity
import kotlinx.coroutines.launch

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
//            val intent = Intent(requireActivity(), InboxActivity::class.java)
//            startActivity(intent)



            lifecycleScope.launch {
                val inboxData = MoEInboxHelper.getInstance().fetchAllMessages(requireActivity())
                Log.d("Utils.moengage", "my inbox data : $inboxData")

                val mediaContent = inboxData!!.inboxMessages[0].mediaContent
                val image = mediaContent!!.url

                Log.d("utils.image", "my image url: $image ")
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

}