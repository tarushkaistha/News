package com.example.myapplication.articlesModel


data class NewsResponse(
    val articles: List<Article>, val status: String
)