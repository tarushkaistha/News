package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.myapplication.R
import com.example.myapplication.articlesViewModel.ArticlesViewModel
import com.example.myapplication.databinding.FragmentArticlesBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.messaging.FirebaseMessaging

class ArticlesFragment : Fragment() {

    private lateinit var binding: FragmentArticlesBinding
    private lateinit var newsAdapter: NewsAdapter
    private val articlesViewModel: ArticlesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            newsAdapter = NewsAdapter {
                val action =
                    ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailFragment(it.url)
                findNavController().navigate(action)
            }

            this.mtToolbar.toolbar.apply {
                title = requireActivity().getString(R.string.newsapp)
                this.inflateMenu(R.menu.sort_news_menu)
                this.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_oldest_news -> {
                            newsAdapter.getOldestNews()
                            true
                        }

                        R.id.action_latest_news -> {
                            newsAdapter.getLatestNews()
                            true
                        }

                        else -> false
                    }
                }
            }

            this.swipeRefreshContainer.setOnRefreshListener(swipeRefreshListener)

            this.newsRv.apply {
                this.layoutManager = LinearLayoutManager(requireActivity())
                this.adapter = newsAdapter
            }

            this.sendNotificationBtn.setOnClickListener {
                FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(
                            "ArticlesFrg", "Fetching FCM registration token failed", task.exception
                        )
                        return@addOnCompleteListener
                    }

                    val token = task.result
                    Log.d("ArticlesFrg", "Resultant token: $token")
                    Toast.makeText(requireActivity(), "Resultant token: $token", Toast.LENGTH_SHORT)
                        .show()

                }
            }

            if (checkGooglePlayServicesAvailability()) {

            } else {
                Log.w("articleFrg", "Device doesn't have google play services")
            }
        }


        articlesViewModel.newsResponse.observe(viewLifecycleOwner) { result ->
            binding.loading.root.visibility = View.VISIBLE

            if (result?.articles != null) {

                newsAdapter.updateNews(result.articles)
                binding.swipeRefreshContainer.isRefreshing = false
                binding.noInternet.root.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
                binding.newsRv.visibility = View.VISIBLE
                binding.sendNotificationBtn.visibility = View.VISIBLE
                binding.breakingNewsTv.visibility = View.VISIBLE
            } else {
                binding.swipeRefreshContainer.isRefreshing = false
                binding.newsRv.visibility = View.GONE
                binding.noInternet.root.visibility = View.VISIBLE
                binding.sendNotificationBtn.visibility = View.GONE
                binding.loading.root.visibility = View.GONE
                binding.breakingNewsTv.visibility = View.GONE
            }

        }

        articlesViewModel.getAllArticles()

    }

    private val swipeRefreshListener = OnRefreshListener {
        binding.swipeRefreshContainer.isRefreshing = true
        articlesViewModel.getAllArticles()
    }

    private fun checkGooglePlayServicesAvailability(): Boolean {
        val status =
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireActivity())
        return if (status != ConnectionResult.SUCCESS) {
            Log.e("articleFrg", "Error")
            false
        } else {
            Log.i("articleFrg", "Google play services updated")
            true
        }
    }

}