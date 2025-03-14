package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentCustomInboxBinding

class CustomInbox : Fragment() {

    private lateinit var binding: FragmentCustomInboxBinding
//    private lateinit var customInboxAdapter: MyCustomInboxAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCustomInboxBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        customInboxAdapter = MyCustomInboxAdapter()
//        binding.inboxRv.apply {
//            this.layoutManager = LinearLayoutManager(requireActivity())
//            this.adapter = customInboxAdapter
//        }

//        lifecycleScope.launch {
//            customInboxAdapter = MyCustomInboxAdapter()
//
//            val myInboxData: InboxData? =
//                MoEInboxHelper.getInstance().fetchAllMessages(requireActivity())
//
//            val myInboxes: List<com.moengage.inbox.core.model.InboxMessage> =
//                myInboxData!!.inboxMessages
//
//            if (myInboxes.isNotEmpty()) {
//                Log.d("my inbox list", "inbox has contents: $myInboxes")
//                customInboxAdapter.updateInbox(myInboxes)
////                binding.inboxRv.visibility = View.VISIBLE
//                binding.noNotificationsTv.visibility = View.GONE
//            }
//
//
//        }


    }

}