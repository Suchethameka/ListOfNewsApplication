package com.example.listofnewsapplication.presenter

import com.example.listofnewsapplication.data.News

interface NewsContract {
    interface View {
        fun showNews(newsList: List<News>)
        fun updateSavedData(newsItem: News)

    }

    interface Presenter {
        fun searchNews(query: String)
        fun fetchNews()
        fun onSaveClick(newsItem: News)

    }
}
