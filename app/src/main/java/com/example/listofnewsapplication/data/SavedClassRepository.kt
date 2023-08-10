package com.example.listofnewsapplication.data

class SavedNewsRepository(private val databaseHelper: DataBaseHelper) {
    fun getAllSavedNewsItems(): List<News> {
        return databaseHelper.getAllNewsItems()
    }

    fun saveNewsItem(newsItem: News) {
        databaseHelper.insertNewsItem(newsItem)
    }
}
