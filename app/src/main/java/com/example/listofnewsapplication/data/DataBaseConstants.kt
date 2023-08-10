package com.example.listofnewsapplication.data

object DataBaseConstants {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "NewsDatabase"
    const val NEWS_TABLE_NAME = "News"
    // Add other constants as needed

    const val ID = "id"
    const val AUTHOR = "author"
    const val CATEGORY = "category"
    const val DESCRIPTION = "description"
    const val IMAGE = "image"
    const val LANGUAGE = "language"
    const val PUBLISHED = "published"
    const val TITLE = "title"
    const val URL = "url"

    val CREATE_NEWS_TABLE = """
        CREATE TABLE $NEWS_TABLE_NAME(
        $ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $AUTHOR TEXT,
        $CATEGORY TEXT,
        $DESCRIPTION TEXT,
        $IMAGE TEXT,
        $LANGUAGE TEXT,
        $PUBLISHED TEXT,
        $TITLE TEXT,
        $URL TEXT)
    """.trimIndent()
}
