package com.example.newsapi

data class Article(
    val id: String,
    val author:String,
    val title:String,
    val description:String,
    val url:String,
    val content:String,
    val urlToImage:String,
    val imageURL: String)
