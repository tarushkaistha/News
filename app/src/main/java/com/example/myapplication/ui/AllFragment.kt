package com.example.myapplication.ui

//import com.moengage.cards.core.MoECardHelper
//import com.moengage.cards.core.listener.CardAvailableListener
//import com.moengage.cards.core.model.Card
//import com.moengage.cards.core.model.CardData
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.Utils
import com.example.myapplication.databinding.FragmentAllBinding
import com.moengage.cards.core.MoECardHelper
import com.moengage.cards.core.listener.CardAvailableListener
import com.moengage.cards.core.model.CardData
import kotlinx.coroutines.launch

class AllFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding

    //    private lateinit var defaultCardAdapter: DefaultCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {

            val allCategory = arguments?.getString("cat_all")
            Log.d(Utils.MOENGAGE_TAG, "onViewCreated with category: $allCategory ")



            MoECardHelper.fetchCards(requireActivity(), object : CardAvailableListener {
                override fun onCardAvailable(cardData: CardData?) {

                    Log.d(Utils.MOENGAGE_TAG, "my card data: $cardData")
                    Log.d(Utils.MOENGAGE_TAG, "my card data with cards having tag: ${cardData!!.cards}")


                }

            })
//            MoECardHelper.fetchCards(requireActivity(), object : CardAvailableListener {
//                override fun onCardAvailable(cardData: CardData?) {
//
//                    Log.d(Utils.MOENGAGE_TAG, "my card data : $cardData")
//                    val myCards = cardData?.cards
//                    Log.d(Utils.MOENGAGE_TAG, "my cards : $myCards")
//
////                    defaultCardAdapter = DefaultCardAdapter(object : DefaultCardAdapter.CardDelete {
////                        override fun onCardDelete(
////                            position: Int, cards: ArrayList<Card>, card: Card
////                        ): ArrayList<Card> {
////                            val updatedCars = defaultCardAdapter.deleteItem(position, cards)
////                            return hideUpdateCardsUi(updatedCars)
////                        }
////
////                    })
////
////                    if (myCards?.isNotEmpty() == true) {
////
////                        Log.d(Utils.MOENGAGE_TAG, "my cards in adapter : $myCards")
////
////                        defaultCardAdapter.showLatestCards(myCards)
////                        binding.customCatRv.visibility = View.VISIBLE
////                        binding.oopsEmptyCardsTv.visibility = View.GONE
////                    } else {
////
////                        binding.oopsEmptyCardsTv.visibility = View.VISIBLE
////                        binding.customCatRv.visibility = View.GONE
////
////                    }
//
//                    if (myCards?.isNotEmpty() == true) {
//
//                        Log.d(Utils.MOENGAGE_TAG, "my cards in adapter : $myCards")
//
//                        defaultCardAdapter.showLatestCards(myCards)
//                        binding.customCatRv.visibility = View.VISIBLE
//                        binding.oopsEmptyCardsTv.visibility = View.GONE
//                    } else {
//
//                        binding.oopsEmptyCardsTv.visibility = View.VISIBLE
//                        binding.customCatRv.visibility = View.GONE
//
//                    }
//                }
//
//            })

//            defaultCardAdapter = DefaultCardAdapter(object : DefaultCardAdapter.CardDelete {
//                override fun onCardDelete(
//                    position: Int, cards: ArrayList<Card>, card: Card
//                ): ArrayList<Card> {
//                    val updatedCars = defaultCardAdapter.deleteItem(position, cards)
//                    return hideUpdateCardsUi(updatedCars)
//                }
//
//            })

//            if (myCards?.isNotEmpty() == true) {
//
//                Log.d(Utils.MOENGAGE_TAG, "my cards in adapter : $myCards")
//
//                defaultCardAdapter.showLatestCards(myCards)
//                binding.customCatRv.visibility = View.VISIBLE
//                binding.oopsEmptyCardsTv.visibility = View.GONE
//            } else {
//
//                binding.oopsEmptyCardsTv.visibility = View.VISIBLE
//                binding.customCatRv.visibility = View.GONE
//
//            }

            val cardData: CardData? =
                MoECardHelper.getCardsForCategory(requireContext(), allCategory!!)
//            Log.d(Utils.MOENGAGE_TAG, "my card data with category: $cardData")

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