package com.example.listofnewsapplication.presenter

import com.example.listofnewsapplication.data.News

interface SavedPresenter {
    fun loadSavedNews()
    fun saveNewsItem(newsItem: News)
}
