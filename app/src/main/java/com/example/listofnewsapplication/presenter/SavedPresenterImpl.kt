package com.example.listofnewsapplication.presenter

import com.example.listofnewsapplication.data.News
import com.example.listofnewsapplication.data.SavedNewsRepository
import com.example.listofnewsapplication.view.SavedView

class SavedPresenterImpl(private val view: SavedView, private val repository: SavedNewsRepository) : SavedPresenter {

    override fun loadSavedNews() {
        val savedNewsList = repository.getAllSavedNewsItems()
        view.showSavedNews(savedNewsList)
    }

    override fun saveNewsItem(newsItem: News) {
        repository.saveNewsItem(newsItem)
        view.onNewsItemSaved()
    }
}
