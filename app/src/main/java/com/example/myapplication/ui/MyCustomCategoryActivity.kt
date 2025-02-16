package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Utils.Companion.MOENGAGE_TAG
import com.example.myapplication.databinding.FragmentMyCustomCategoryBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.moengage.cards.core.CARDS_CATEGORY_ALL
import com.moengage.cards.core.MoECardHelper
import com.moengage.cards.core.listener.SyncCompleteListener
import com.moengage.cards.core.model.SyncCompleteData

//import com.moengage.cards.core.CARDS_CATEGORY_ALL
//import com.moengage.cards.core.MoECardHelper
//import com.moengage.cards.core.listener.SyncCompleteListener
//import com.moengage.cards.core.model.SyncCompleteData


class MyCustomCategoryActivity : AppCompatActivity() {

    private lateinit var binding: FragmentMyCustomCategoryBinding

    private val allFragment = AllFragment()
    private val announcementFragment = AnnouncementFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMyCustomCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MoECardHelper.onCardSectionLoaded(this, object : SyncCompleteListener {
            override fun onSyncComplete(data: SyncCompleteData?) {
                Log.d(MOENGAGE_TAG, "onSyncComplete data: $data")
            }

        })


        val cardCats = getCardCategories(this)
        val tabs = binding.tabs as TabLayout

        when {
            cardCats.isEmpty() -> {
                binding.categoryContainer.visibility = View.GONE
            }

            else -> {
                binding.categoryContainer.visibility = View.VISIBLE
                binding.emptyCardsTv.visibility = View.GONE
                tabs.visibility = View.VISIBLE

                tabs.addTab(tabs.newTab().setText(cardCats.elementAt(0)), true)

                for (i in 1 until cardCats.size) {
                    tabs.addTab(tabs.newTab().setText(cardCats.elementAt(i)))
                }

                tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        when (tab?.position) {
                            0 -> {
                                binding.categoryContainer.visibility = View.VISIBLE
                                binding.emptyCardsTv.visibility = View.GONE
                                val bundle = Bundle()
                                val allCategory = cardCats.elementAt(0)
                                bundle.putString("cat_all", allCategory)
                                val fm = supportFragmentManager
                                val ft = fm.beginTransaction()
                                ft.replace(binding.categoryContainer.id, allFragment)
                                ft.commit()
                                allFragment.arguments = bundle
                            }
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }

                })

                val allCategory = cardCats.elementAt(0)
                val bundle = Bundle()
                bundle.putString("cat_all", allCategory)
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                ft.replace(binding.categoryContainer.id, allFragment)
                ft.commit()
                allFragment.arguments = bundle
            }
        }
//        when {
//            cardCats.isEmpty() -> {
//                binding.categoryContainer.visibility = View.GONE
//                binding.emptyCardsTv.visibility = View.VISIBLE
//            }
//
//            cardCats.size == 1 -> {
//                // show the default fragment i.e. all
//            }
//
////            else -> {
////                binding.categoryContainer.visibility = View.VISIBLE
////                binding.emptyCardsTv.visibility = View.GONE
////                tabs.visibility = View.VISIBLE
////                tabs.addTab(tabs.newTab().setText(cardCats.elementAt(0)), true)
////
////                for (i in 1 until cardCats.size) {
////                    tabs.addTab(tabs.newTab().setText(cardCats.elementAt(i)))
////                }
////
////                tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
////                    override fun onTabSelected(p0: TabLayout.Tab?) {
////                        when (p0?.position!!) {
////                            0 -> {
////                                binding.categoryContainer.visibility = View.VISIBLE
////                                binding.emptyCardsTv.visibility = View.GONE
////                                val bundle = Bundle()
////                                val allCategory = cardCats.elementAt(0)
////                                bundle.putString("cat_all", allCategory)
////                                val fm = supportFragmentManager
////                                val ft = fm.beginTransaction()
////                                ft.replace(binding.categoryContainer.id, allFragment)
////                                ft.commit()
////                                allFragment.arguments = bundle
////                            }
////
////                            1 -> {
////                                binding.emptyCardsTv.visibility = View.GONE
////                                binding.categoryContainer.visibility = View.VISIBLE
////                                val bundle = Bundle()
////                                val announcementCategory = cardCats.elementAt(1)
////                                bundle.putString("cat_announcement", announcementCategory)
////                                val fm = supportFragmentManager
////                                val ft = fm.beginTransaction()
////                                ft.replace(binding.categoryContainer.id, announcementFragment)
////                                ft.commit()
////                                announcementFragment.arguments = bundle
////                            }
////
////                            2, 3, 4 -> {
////                                binding.emptyCardsTv.visibility = View.VISIBLE
////                                binding.categoryContainer.visibility = View.GONE
////                            }
////                        }
////
////                    }
////
////                    override fun onTabUnselected(p0: TabLayout.Tab?) {
////
////                    }
////
////                    override fun onTabReselected(p0: TabLayout.Tab?) {
////
////                    }
////
////                })
////
////                val bundle = Bundle()
////                val allCategory = cardCats.elementAt(0)
////                bundle.putString("cat_all", allCategory)
////                val fm = supportFragmentManager
////                val ft = fm.beginTransaction()
////                ft.add(binding.categoryContainer.id, allFragment)
////                ft.commit()
////                allFragment.arguments = bundle
////
////            }
////        }

    }


    override fun onStop() {
        super.onStop()

        MoECardHelper.onCardSectionUnloaded(this)

    }

    private fun getCardCategories(context: Context): List<String> {
        val categories = MoECardHelper.getCardCategories(context)
        val shouldShowAllTab = MoECardHelper.isAllCategoryEnabled(context)
        if (!shouldShowAllTab) return categories
        val allCategories = mutableListOf<String>(CARDS_CATEGORY_ALL).apply {
            addAll(categories)
        }
        return allCategories.toList()
    }
}