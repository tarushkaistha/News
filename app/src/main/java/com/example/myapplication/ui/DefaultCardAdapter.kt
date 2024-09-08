package com.example.myapplication.ui


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.databinding.SingleCardItemBinding
import com.moengage.cards.core.MoECardHelper
import com.moengage.cards.core.model.Card
import com.moengage.cards.core.model.action.NavigationAction
import com.moengage.cards.core.model.enums.ActionType
import com.moengage.cards.core.model.enums.NavigationType
import com.moengage.cards.core.model.enums.WidgetType
import com.moengage.core.internal.model.ViewDimension
import com.moengage.core.internal.utils.getDeviceDimensions
import com.moengage.core.internal.utils.isLandscapeMode
import com.moengage.core.internal.utils.isTablet
import com.moengage.core.internal.utils.transformToPx


class DefaultCardAdapter(private val cardDelete: CardDelete) :
    RecyclerView.Adapter<DefaultCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SingleCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    private val cards = arrayListOf<Card>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val defaultInboxMessage = cards[position]

        holder.bind(defaultInboxMessage)

        MoECardHelper.cardShown(holder.binding.root.context, defaultInboxMessage)

    }

    override fun getItemCount(): Int {
        return cards.size
    }

    inner class ViewHolder(
        val binding: SingleCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            val cardContainer = card.template.containers[0]
            Log.d(Utils.MOENGAGE_TAG, "my card container : $cardContainer")


            for (widget in cardContainer.widgetList) {
                Log.d(Utils.MOENGAGE_TAG, "my card widget : $widget")
                if (widget.id == 0 && widget.widgetType == WidgetType.IMAGE) {
                    val myImageDimens = getImageViewDimensions(binding.root.context)
                    if (widget.content.isEmpty()) {
                        Log.d(Utils.MOENGAGE_TAG, "unable to show the $widget ")
                        return
                    }
                    binding.imageView.layoutParams.width = myImageDimens.width
                    binding.imageView.layoutParams.height = myImageDimens.height
                    binding.imageView.visibility = View.VISIBLE
                    if (widget.content.isNotBlank() && widget.content.lowercase()
                            .endsWith(".gif")
                    ) {
//                        Glide.with(binding.root.context).asGif().load(widget.content).centerCrop()
//                            .placeholder(R.drawable.no_image).into(binding.imageView)
                    } else {
//                        Glide.with(binding.root.context).load(widget.content).centerCrop()
//                            .placeholder(R.drawable.no_image).into(binding.imageView)
                    }

                } else if (widget.id == 1 && widget.widgetType == WidgetType.TEXT) {
                    binding.header.text = HtmlCompat.fromHtml(
                        widget.content, HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                } else if (widget.id == 2 && widget.widgetType == WidgetType.TEXT) {
                    if (widget.content.isEmpty()) {
                        Log.d(
                            Utils.MOENGAGE_TAG,
                            "in on bind empty ${widget.content} cannot show widget"
                        )
                        continue
                    }

                    binding.message.text = HtmlCompat.fromHtml(
                        widget.content, HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                } else if (widget.id == 3 && widget.widgetType == WidgetType.BUTTON) {
                    if (widget.content.isEmpty()) {
                        Log.d(Utils.MOENGAGE_TAG, "unable to show the $widget")
                        return
                    }

                    binding.ctaButton.text = HtmlCompat.fromHtml(
                        widget.content, HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                    binding.ctaButton.setOnClickListener {
                        binding.ctaButton.visibility = View.VISIBLE
                        binding.unClickedIndicator.visibility = View.GONE
                        MoECardHelper.cardClicked(binding.root.context, card, widget.id)
                        val ctaActions = widget.actionList
                        for (action in ctaActions) {
                            val actionNavigation = (action as NavigationAction)
                            if (action.actionType == ActionType.NAVIGATE && actionNavigation.navigationType == NavigationType.DEEPLINK) {
                                val googleUrl = action.value
                                val googleIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleUrl))
                                binding.root.context.startActivity(googleIntent)
                            } else if (action.actionType == ActionType.NAVIGATE && actionNavigation.navigationType == NavigationType.SCREENNAME) {
                                val customInboxIntent =
                                    Intent(binding.root.context, Class.forName(action.value))
                                binding.root.context.startActivity(customInboxIntent)
                            }
                        }
                    }

                }

                if (!card.metaData.campaignState.isClicked) {
                    MoECardHelper.getUnClickedCardCount(binding.root.context)?.count
                    Toast.makeText(
                        binding.root.context,
                        "i have ${MoECardHelper.getUnClickedCardCount(binding.root.context)?.count} unclicked cards",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                binding.root.setOnClickListener {
                    binding.unClickedIndicator.visibility = View.GONE
                    MoECardHelper.cardClicked(binding.root.context, card, widget.id)

                }
            }

            if (!card.metaData.campaignState.isClicked) {
                binding.unClickedIndicator.visibility = View.VISIBLE
            }


            val bgColor = cardContainer.style?.backgroundColor
            if (bgColor?.isNotEmpty() == true) {
                binding.root.setBackgroundColor(Color.parseColor(bgColor))
            }


            binding.root.setOnLongClickListener {
                val dialog = AlertDialog.Builder(binding.root.context).setItems(
                    arrayOf(
                        binding.root.context.getString(com.moengage.cards.ui.R.string.moe_card_delete_title)
                    )
                ) { _: DialogInterface, _: Int ->
                    MoECardHelper.deleteCard(binding.root.context, card)
                    cardDelete.onCardDelete(bindingAdapterPosition, cards, card)
                }
                dialog.create().show()
                true
            }

        }

    }

    private fun getImageViewDimensions(context: Context): ViewDimension {
        val deviceDimensions = getDeviceDimensions(context)
        val availableWidth = deviceDimensions.width - (transformToPx(context, 8.0) * 2)
        Log.d(Utils.MOENGAGE_TAG, "getImageViewDimensions() : Device Dimension: $deviceDimensions")
        return if (isTablet(context) || isLandscapeMode(context)) {
            Log.d(
                Utils.MOENGAGE_TAG, "getImageViewDimensions() : Device Dimension: $deviceDimensions"
            )
            val dimension = availableWidth / 10
            val imageDimension = ViewDimension(
                dimension, dimension
            )
            Log.d(
                Utils.MOENGAGE_TAG, "getImageViewDimensions() : Device Dimension: $deviceDimensions"
            )
            imageDimension
        } else {
            Log.d(
                Utils.MOENGAGE_TAG, "getImageViewDimensions() : Device Dimension: $deviceDimensions"
            )
            val dimension = availableWidth / 6
            val imageDimension = ViewDimension(
                dimension, dimension
            )
            Log.d(
                Utils.MOENGAGE_TAG, "getImageViewDimensions() : Device Dimension: $deviceDimensions"
            )
            imageDimension
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showLatestCards(list: List<Card>?) {
        Log.d("adapter", "my latest cards list: $list ")
        cards.addAll(list!!)

        notifyDataSetChanged()
    }

    interface CardDelete {
        fun onCardDelete(
            position: Int, cards: ArrayList<Card>, card: Card
        ): ArrayList<Card>
    }

    fun deleteItem(position: Int, cards: ArrayList<Card>): ArrayList<Card> {
        cards.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, cards.size)
        return cards
    }


}