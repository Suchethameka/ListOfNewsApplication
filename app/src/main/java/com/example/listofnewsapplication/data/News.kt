package com.example.listofnewsapplication.data

data class News(
    val author: String,
    val category: List<String>,
    val description: String,
    val id: String,
    var image: String,
    val language: String,
    val published: String,
    val title: String,
    val url: String
)