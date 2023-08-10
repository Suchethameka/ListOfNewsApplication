package com.example.listofnewsapplication.presenter

import com.example.listofnewsapplication.view.HomeFragment
import com.example.listofnewsapplication.model.NewsModel
import com.example.listofnewsapplication.data.News


class NewsPresenter(private val view: HomeFragment, private val model: NewsModel) : NewsContract.Presenter {

    override fun fetchNews() {
        model.fetchNews { newsList ->
            view.showNews(newsList)
        }
    }

    override fun onSaveClick(newsItem: News) {
        model.insertNews(newsItem)
        view.updateSavedData(newsItem)
    }

    override fun searchNews(query: String) {
        val searchResults = model.searchNewsByQuery(query) // Call the function on the model
        view.showSearchResults(searchResults)
    }
}


