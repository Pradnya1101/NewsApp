package com.example.newsapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="https://newsapi.org/"
const val API_KEY="eb2f59e54b5c4f6eaf6ad56a9a556b17"

interface NewsInterface{

    @GET("v2/top-headlines?country=us&category=business&apiKey=$API_KEY")
    fun getHeadLines(@Query("country")country:String = "in", @Query("page")page:Int, @Query("category")category:String): Call<News>
}

object NewService{
    val newsInstance:NewsInterface
    init{
        val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}