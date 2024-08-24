package com.example.news.models

data class RemoteResult(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
