package com.example.myapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.articlesModel.Article
import com.example.myapplication.databinding.SingleNewsItemBinding
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import java.text.SimpleDateFormat
import java.util.Locale

class NewsAdapter(private var onItemClicked: ((article: Article) -> Unit)) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SingleNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    private val articles = arrayListOf<Article>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(
        val binding: SingleNewsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.newsTitle.text = article.title
//            Glide.with(binding.root.context).load(article.urlToImage).into(binding.img)

            binding.newsSourceTv.text = article.source.name
            binding.root.setOnClickListener {
                onItemClicked(article)
            }


            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy MMM dd HH:mm:ss a", Locale.getDefault())
            val publishedDate = inputFormat.parse(article.publishedAt)
            val formattedDate = outputFormat.format(publishedDate!!)
            binding.newsPublicationTime.text = formattedDate
            binding.newsPublicationTime.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(
                    binding.root.context, R.drawable.time_drawable
                ), null, null, null
            )

        }

    }

    fun updateNews(list: List<Article>) {
        Log.d("adapter", "updateNews: new list: $list ")
        articles.clear()
        val filteredList = list.filter {
            it.author != null && (it.urlToImage.contains("png") || it.urlToImage.contains(
                "jpg"
            )) && it.content != null && it.description != null && it.source.name != null && it.source.id != null
        }
        Log.d("adapter", "updateNews: new filtered list: $filteredList ")
        articles.addAll(filteredList)
        notifyDataSetChanged()
    }

    fun getOldestNews() {

        val oldestNews = articles.sortedBy {
            it.publishedAt
        }
        Log.d("adapter", "getOldestNews: $oldestNews ")
        updateNews(oldestNews)

    }

    fun getLatestNews() {
        val latestNews = articles.sortedByDescending {
            it.publishedAt
        }

        Log.d("adapter", "getOldestNews: $latestNews ")
        updateNews(latestNews)
    }

}