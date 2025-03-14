package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentCustomInboxBinding
import com.example.myapplication.notificationInbox.MyInboxMessage
import com.moengage.inbox.core.MoEInboxHelper
import com.moengage.inbox.core.model.InboxData
import com.moengage.inbox.core.model.InboxMessage
import kotlinx.coroutines.launch

class CustomInboxActivity : AppCompatActivity() {

    private lateinit var binding: FragmentCustomInboxBinding
    private lateinit var customInboxAdapter: MyCustomInboxAdapter
    private lateinit var inboxes: ArrayList<MyInboxMessage>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentCustomInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            val inboxData: InboxData? =
                MoEInboxHelper.getInstance().fetchAllMessages(this@CustomInboxActivity)

            val inboxMessages: List<InboxMessage> = inboxData!!.inboxMessages

            inboxes = inboxMessages.map {
                MyInboxMessage(title = it.textContent.title)
            } as ArrayList<MyInboxMessage>

        }
        customInboxAdapter = MyCustomInboxAdapter(inboxes)
        customInboxAdapter.updateInbox(inboxes)
        binding.inboxRv.apply {
            this.layoutManager = LinearLayoutManager(this@CustomInboxActivity)
            this.adapter = customInboxAdapter

        }

    }


}