package com.example.myapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.databinding.SingleInboxItemBinding
import com.moengage.inbox.core.MoEInboxHelper


class CustomInboxAdapter(private val messageDeleteCallback: messageDelete) :
    RecyclerView.Adapter<CustomInboxAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            SingleInboxItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    private val defaultInboxMessages = arrayListOf<com.moengage.inbox.core.model.InboxMessage>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val defaultInboxMessage = defaultInboxMessages[position]

        holder.bind(defaultInboxMessage)

    }

    override fun getItemCount(): Int {
        return defaultInboxMessages.size
    }

    inner class ViewHolder(
        val binding: SingleInboxItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: com.moengage.inbox.core.model.InboxMessage) {
            binding.notificationTitle.text = article.textContent.title

            binding.notificationTitle.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context, if (article.isClicked) R.color.white else R.color.grey
                )
            )
//            if (MoEInboxHelper.getInstance().hasCouponCode(article)) {
//                MoEInboxHelper.getInstance().getCouponCode(article)
//                binding.notificationCouponCode.text = article.payload.optString("gcm_coupon_code")
//            } else {
//                binding.notificationCouponCode.visibility = View.GONE
//            }


            if (!article.isClicked) {
                val unClickedCountData =
                    MoEInboxHelper.getInstance().getUnClickedMessagesCount(binding.root.context)
                Log.d(
                    Utils.MOENGAGE_TAG, "unclicked messages count : ${unClickedCountData!!.count}"
                )
            }

            binding.notificationContent.setOnClickListener {
                article.isClicked = true
                binding.notificationTitle.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context, R.color.white
                    )
                )

                MoEInboxHelper.getInstance().trackMessageClicked(binding.root.context, article)


            }

            binding.deleteBtn.setOnClickListener {

                MoEInboxHelper.getInstance().deleteMessage(binding.root.context, article)

                messageDeleteCallback.onMessageDelete(
                    bindingAdapterPosition, defaultInboxMessages, article
                )


            }
        }

    }

    fun showInboxNotifications(list: List<com.moengage.inbox.core.model.InboxMessage>?) {
        Log.d("adapter", "my inbox list: $list ")
        defaultInboxMessages.addAll(list!!)
        notifyDataSetChanged()
    }

    interface messageDelete {
        fun onMessageDelete(
            position: Int,
            inboxMessages: ArrayList<com.moengage.inbox.core.model.InboxMessage>,
            inboxMessage: com.moengage.inbox.core.model.InboxMessage
        ): ArrayList<com.moengage.inbox.core.model.InboxMessage>
    }

    fun deleteItem(
        position: Int,
        inboxMessage: com.moengage.inbox.core.model.InboxMessage,
        inboxMessages: ArrayList<com.moengage.inbox.core.model.InboxMessage>
    ): ArrayList<com.moengage.inbox.core.model.InboxMessage> {
        defaultInboxMessages.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, defaultInboxMessages.size)
        return inboxMessages
    }


}