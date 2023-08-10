package com.example.listofnewsapplication.data

import com.example.listofnewsapplication.data.News

data class NewsResponse(
    val news: List<News>,
    val page: Int,
    val status: String
)