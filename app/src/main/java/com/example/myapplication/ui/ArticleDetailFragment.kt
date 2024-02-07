package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentArticleDetailBinding


class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding
    private val args: ArticleDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleUrl = args.articleUrl

        Log.d("articledetail", "article url: $articleUrl")


        binding.webView.apply {
            settings.apply {
                domStorageEnabled = true
                javaScriptEnabled = true
            }
            webViewClient = MyWebViewClient()
            if (articleUrl.isNotEmpty()) {
                binding.webView.loadUrl(articleUrl)
            }

        }

        binding.mtToolbar.toolbar.apply {
            title = requireActivity().getString(R.string.read_news)
            navigationIcon = ContextCompat.getDrawable(requireActivity(), R.drawable.back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

    }


    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?, request: WebResourceRequest?
        ): Boolean {
            return false
        }

    }


}