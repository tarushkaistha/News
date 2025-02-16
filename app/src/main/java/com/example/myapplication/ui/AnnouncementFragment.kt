package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Utils
import com.example.myapplication.databinding.FragmentAnnouncementBinding
//import com.moengage.cards.core.MoECardHelper
//import com.moengage.cards.core.model.Card
//import com.moengage.cards.core.model.CardData
import kotlinx.coroutines.launch

class AnnouncementFragment : Fragment() {


    private lateinit var binding: FragmentAnnouncementBinding

//    private lateinit var defaultCardAdapter: DefaultCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnnouncementBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {

            val announcementCategory = arguments?.getString("cat_announcement")
            Log.d(Utils.MOENGAGE_TAG, "onViewCreated with category: $announcementCategory ")


//            val cardData: CardData? =
//                MoECardHelper.getCardsForCategory(requireContext(), announcementCategory!!)
//            Log.d(Utils.MOENGAGE_TAG, "my card data with category: $cardData")
//
//            val myCardsWithCats = cardData?.cards

//            defaultCardAdapter = DefaultCardAdapter(object : DefaultCardAdapter.CardDelete {
//                override fun onCardDelete(
//                    position: Int, cards: ArrayList<Card>, card: Card
//                ): ArrayList<Card> {
//                    val updatedCars = defaultCardAdapter.deleteItem(position, cards)
//                    return hideUpdateCardsUi(updatedCars)
//                }
//
//            })


//            if (myCardsWithCats?.isNotEmpty() == true) {
//
//                Log.d(Utils.MOENGAGE_TAG, "my cards with category : $myCardsWithCats")
//
//                defaultCardAdapter.showLatestCards(myCardsWithCats)
//                binding.customCatRv.visibility = View.VISIBLE
//                binding.oopsEmptyCardsTv.visibility = View.GONE
//            } else {
//
//                binding.oopsEmptyCardsTv.visibility = View.VISIBLE
//                binding.customCatRv.visibility = View.GONE
//
//            }

        }

//        this.binding.customCatRv.apply {
//            this.layoutManager = LinearLayoutManager(requireContext())
//            this.adapter = defaultCardAdapter
//        }
    }

//    fun hideUpdateCardsUi(list: ArrayList<Card>): ArrayList<Card> {
//
//        if (list.isEmpty()) {
//            binding.oopsEmptyCardsTv.visibility = View.VISIBLE
//            binding.customCatRv.visibility = View.GONE
//        } else {
//            binding.oopsEmptyCardsTv.visibility = View.GONE
//            binding.customCatRv.visibility = View.VISIBLE
//        }
//
//        return list
//    }

}