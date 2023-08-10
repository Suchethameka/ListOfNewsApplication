package com.example.listofnewsapplication.model

import android.content.Context

class VolleyHandler(private val context: Context) {
    companion object{
        const val BASE_URL_NEWS =  "https://api.currentsapi.services/v1/latest-news"
    }
}