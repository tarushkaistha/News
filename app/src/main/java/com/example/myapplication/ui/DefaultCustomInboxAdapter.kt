package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.moengage.inbox.core.model.InboxMessage
import com.moengage.inbox.ui.adapter.InboxAdapter
import com.moengage.inbox.ui.adapter.InboxListAdapter
import com.moengage.inbox.ui.adapter.ViewHolder

class DefaultCustomInboxAdapter() : InboxAdapter() {

    inner class MyDefaultInboxCustomViewHolder(
        val view: View
    ) : ViewHolder(view) {
        private val messageView: TextView = view.findViewById(R.id.moeMessage)
        private val titleView: TextView = view.findViewById(R.id.moeTitle)
        private val deleteBtn: Button = view.findViewById(R.id.moeDelBtn)
        fun bind(position: Int, inboxMessage: InboxMessage, inboxListAdapter: InboxListAdapter) {
            messageView.text = inboxMessage.textContent.message
            titleView.text = inboxMessage.textContent.title

            view.setBackgroundColor(
                ContextCompat.getColor(
                    view.context, if (inboxMessage.isClicked) R.color.white else R.color.grey
                )
            )

            deleteBtn.setOnClickListener {
                inboxListAdapter.deleteItem(position, inboxMessage)
            }

            view.setOnClickListener {
                inboxListAdapter.onItemClicked(position, inboxMessage)

//                MoEInboxUiHelper.getInstance()
//                    .setOnMessageClickListener(object : OnMessageClickListener {
//                        override fun onMessageClick(data: MessageClickData): Boolean {
//                            return data.inboxMessage.isClicked == true
//                        }
//
//                    })

                view.setBackgroundColor(
                    ContextCompat.getColor(
                        view.context, R.color.white
                    )
                )

            }
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int, inboxMessage: InboxMessage): Int {
        return 1001
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int,
        inboxMessage: InboxMessage,
        inboxListAdapter: InboxListAdapter
    ) {
//        (viewHolder as BasicViewHolder).onBind(position, inboxMessage, inboxListAdapter)

        (viewHolder as MyDefaultInboxCustomViewHolder).bind(
            position, inboxMessage, inboxListAdapter
        )
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return MyDefaultInboxCustomViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.default_custom_inbox_item, viewGroup, false)
        )

//        return BasicViewHolder(
//            LayoutInflater.from(viewGroup.context)
//                .inflate(R.layout.moe_inbox_item_view, viewGroup, false)
//        )
    }


}
