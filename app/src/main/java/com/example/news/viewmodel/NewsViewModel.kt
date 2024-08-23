package com.example.news.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.RetrofitServiceFactory
import com.example.news.models.RemoteResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewsViewModel : ViewModel() {

    private val _news = MutableStateFlow<RemoteResult?>(null)
    val news: StateFlow<RemoteResult?> get() = _news

    private val service = RetrofitServiceFactory.makeRetrofitService()

    fun fetchNews() {
        viewModelScope.launch {
            try {
                val result = service.listNews(
                    "Microsoft",
                    "2024-07-23",
                    "publishedAt",
                    "1d39fbf856b74b6ca9a8c29f997913cf",
                    "es"
                )
                _news.value = result
//                _news.value = null
            } catch (e: Exception) {
                _news.value = null
                Log.e("NewsViewModel", "Error fetching news", e)
            }
        }
    }
}
