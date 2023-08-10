package com.example.listofnewsapplication.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.listofnewsapplication.data.DataBaseHelper
import com.example.listofnewsapplication.data.News
import com.example.listofnewsapplication.data.NewsResponse
import com.google.gson.Gson


interface NewsModel {
    fun fetchNews(callback: (List<News>) -> Unit)
    fun insertNews(news: News)
    fun getAllNews(): List<News>
    fun searchNewsByQuery(query: String): List<News>
}

class NewsRepository(private val context: Context, private val dbHelper: DataBaseHelper) : NewsModel {

    private val requestQueue = Volley.newRequestQueue(context)

    override fun fetchNews(callback: (List<News>) -> Unit) {
        val stringRequest = object : StringRequest(
            Request.Method.GET,
            VolleyHandler.BASE_URL_NEWS,
            { response ->
                val newsResponse = Gson().fromJson(response, NewsResponse::class.java)
                if (newsResponse.status == "ok") {
                    callback(newsResponse.news)
                }
            },
            { error ->
                Log.i("tag", error.toString())
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "mNnL1N3VFziPVP8w29dxLQGmA7YBASQsorxyBUoELjyYUR7U"
                return headers
            }
        }

        requestQueue.add(stringRequest)
    }
    override fun
            searchNewsByQuery(query: String): List<News> {
        val newsList = getAllNews()

        val filteredNewsList = newsList.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
        }

        return filteredNewsList
    }

    override fun insertNews(news: News) {
        dbHelper.insertNewsItem(news)
    }

    override fun getAllNews(): List<News> {
        return dbHelper.getAllNewsItems()
    }
}



