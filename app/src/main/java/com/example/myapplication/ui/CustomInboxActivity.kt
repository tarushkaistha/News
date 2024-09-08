package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.CustomInboxActivityBinding
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inbox.core.MoEInboxHelper
import com.moengage.inbox.core.model.InboxData
import com.moengage.inbox.core.model.InboxMessage
import kotlinx.coroutines.launch

class CustomInboxActivity : AppCompatActivity() {

    private lateinit var binding: CustomInboxActivityBinding
    private lateinit var customInboxAdapter: CustomInboxAdapter
    private lateinit var inboxMessages: List<InboxMessage>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CustomInboxActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            customInboxAdapter = CustomInboxAdapter(object : CustomInboxAdapter.messageDelete {
                override fun onMessageDelete(
                    position: Int,
                    inboxMessages: ArrayList<InboxMessage>,
                    inboxMessage: InboxMessage
                ): ArrayList<InboxMessage> {
                    val emptyInboxMessages = customInboxAdapter.deleteItem(
                        position, inboxMessage, inboxMessages
                    )

                    return updateNotText(emptyInboxMessages)
                }

            })
            lifecycleScope.launch {
                val myInboxData: InboxData? =
                    MoEInboxHelper.getInstance().fetchAllMessages(this@CustomInboxActivity)

                inboxMessages = myInboxData?.inboxMessages!!
                if (inboxMessages.isNotEmpty()) {
//                    Log.d("customInboxActivity", "my inbox messages : $inboxMessages ")
                    customInboxAdapter.showInboxNotifications(inboxMessages)
                    binding.noNotificationsTv.visibility = View.GONE
                } else {
                    binding.inboxRv.visibility = View.GONE
                    binding.noNotificationsTv.visibility = View.VISIBLE
                }
            }


            this.inboxRv.apply {
                this.layoutManager = LinearLayoutManager(this@CustomInboxActivity)
                this.adapter = customInboxAdapter
            }

        }

//        MoEInboxHelper.getInstance().fetchAllMessagesAsync(
//            this@CustomInboxActivity
//        ) { inboxData ->
//            val inboxMessages = inboxData?.inboxMessages
//            if (inboxMessages!!.isNotEmpty()) {
//                Log.d("customInboxActivity", "my inbox messages : $inboxMessages ")
//                val inboxMessage = inboxMessages.map {
//                    MyInbox(
//                        title = it.textContent.title, isClicked = it.isClicked
//                    )
//                }
//                customInboxAdapter.showInboxNotifications(inboxMessage)
//            } else {
//                Toast.makeText(
//                    this@CustomInboxActivity,
//                    "Inbox size : ${inboxMessages.size}",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        }


    }

    fun updateNotText(list: ArrayList<InboxMessage>): ArrayList<InboxMessage> {

        if (list.isEmpty()) {
            binding.noNotificationsTv.visibility = View.VISIBLE
            binding.inboxRv.visibility = View.GONE
        } else {
            binding.noNotificationsTv.visibility = View.GONE
            binding.inboxRv.visibility = View.VISIBLE
        }

        return list
    }

    override fun onStart() {
        super.onStart()
        MoEInAppHelper.getInstance().setInAppContext(setOf("context 2"))
    }

    override fun onStop() {
        super.onStop()
        MoEInAppHelper.getInstance().resetInAppContext()
    }

    override fun onResume() {
        super.onResume()
        MoEInAppHelper.getInstance().showInApp(this)
    }

}