package com.example.newsapi.db

import androidx.room.*
import com.example.newsapi.News
import com.example.newsapi.utils.Constants.NEWS_TABLE

@Dao
interface RoomDao {
    @Insert
    fun insertnews(newsEntity: NewsEntity)

    @Query("select * FROM NEWS_TABLE")
    //fun getNews(): List<NewsEntity>?
    fun getNews(): List<NewsEntity>

    @Update
    fun updateNews(newsEntity: NewsEntity)

    @Delete
    fun deleteNews(newsEntity: NewsEntity)

}