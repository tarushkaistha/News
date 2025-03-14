package com.example.myapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.SingleInboxItemBinding
import com.example.myapplication.notificationInbox.MyInboxMessage
import com.moengage.inbox.core.model.InboxMessage

class MyCustomInboxAdapter(private val myInboxes: ArrayList<MyInboxMessage>) :
    RecyclerView.Adapter<MyCustomInboxAdapter.MyCustomViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyCustomViewHolder {

        val view =
            SingleInboxItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomViewHolder(view)
    }

    private val myInboxMessages = arrayListOf<MyInboxMessage>()

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        val inboxMessage = myInboxMessages[position]
        holder.bind(inboxMessage)
    }

    override fun getItemCount(): Int {
        return myInboxMessages.size
    }

    inner class MyCustomViewHolder(
        val binding: SingleInboxItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(inboxMessage: MyInboxMessage) {

            binding.notificationTitle.text = inboxMessage.title

        }

    }

    fun updateInbox(list: List<MyInboxMessage>) {

        Log.d("adapter", "updateInbox: new list: $list ")
        myInboxMessages.addAll(list)
        notifyDataSetChanged()

    }
}