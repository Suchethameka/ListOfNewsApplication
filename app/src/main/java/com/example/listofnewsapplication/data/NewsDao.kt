package com.example.listofnewsapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor

class NewsDao(private val dbHelper: DataBaseHelper) {

    fun insertNews(news: News) {
        val values = ContentValues().apply {
            put(DataBaseConstants.AUTHOR, news.author)
            put(DataBaseConstants.CATEGORY, news.category.joinToString(","))
            put(DataBaseConstants.DESCRIPTION, news.description)
            put(DataBaseConstants.IMAGE, news.image)
            put(DataBaseConstants.LANGUAGE, news.language)
            put(DataBaseConstants.PUBLISHED, news.published)
            put(DataBaseConstants.TITLE, news.title)
            put(DataBaseConstants.URL, news.url)
        }

        dbHelper.writableDatabase.insert(DataBaseConstants.NEWS_TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun getAllNews(): List<News> {
        val newsList = mutableListOf<News>()
        val cursor: Cursor = dbHelper.readableDatabase.query(
            DataBaseConstants.NEWS_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val author = getString(getColumnIndex(DataBaseConstants.AUTHOR))
                val category = getString(getColumnIndex(DataBaseConstants.CATEGORY)).split(",")
                val description = getString(getColumnIndex(DataBaseConstants.DESCRIPTION))
                val image = getString(getColumnIndex(DataBaseConstants.IMAGE))
                val language = getString(getColumnIndex(DataBaseConstants.LANGUAGE))
                val published = getString(getColumnIndex(DataBaseConstants.PUBLISHED))
                val title = getString(getColumnIndex(DataBaseConstants.TITLE))
                val url = getString(getColumnIndex(DataBaseConstants.URL))

                val news = News(author, category, description, "", image, language, published, title, url)
                newsList.add(news)
            }
            close()
        }
        return newsList
    }


}
