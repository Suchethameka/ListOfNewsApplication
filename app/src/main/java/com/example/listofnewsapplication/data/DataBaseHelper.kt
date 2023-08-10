package com.example.listofnewsapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, "NewsDatabase", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DataBaseConstants.CREATE_NEWS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS news")
        onCreate(db)
    }

    fun insertNewsItem(newsItem: News) {
        val values = ContentValues().apply {
            put("author", newsItem.author)
            put("category", newsItem.category.joinToString(","))
            put("description", newsItem.description)
            put("image", newsItem.image)
            put("language", newsItem.language)
            put("published", newsItem.published)
            put("title", newsItem.title)
            put("url", newsItem.url)
        }

        writableDatabase.insert("news", null, values)
    }

    @SuppressLint("Range")
    fun getAllNewsItems(): List<News> {
        val newsList = mutableListOf<News>()

        val cursor = readableDatabase.query("news", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val author = cursor.getString(cursor.getColumnIndex("author"))
            val category = cursor.getString(cursor.getColumnIndex("category")).split(",")
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val image = cursor.getString(cursor.getColumnIndex("image"))
            val language = cursor.getString(cursor.getColumnIndex("language"))
            val published = cursor.getString(cursor.getColumnIndex("published"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val url = cursor.getString(cursor.getColumnIndex("url"))

            val newsItem = News(author, category, description, "", image, language, published, title, url)
            newsList.add(newsItem)
        }
        cursor.close()

        return newsList
    }
}

