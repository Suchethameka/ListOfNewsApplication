package com.example.listofnewsapplication.view

import com.example.listofnewsapplication.data.News


interface NewsView {
    fun showNews(newsList: List<News>)
    fun showSearchResults(searchResults: List<News>)
}



