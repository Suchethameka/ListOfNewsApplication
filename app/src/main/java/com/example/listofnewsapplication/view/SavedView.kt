package com.example.listofnewsapplication.view

import com.example.listofnewsapplication.data.News

interface SavedView {
    fun showSavedNews(newsList: List<News>)
    fun onNewsItemSaved()
}
