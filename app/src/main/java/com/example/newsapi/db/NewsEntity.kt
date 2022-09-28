package com.example.newsapi.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapi.utils.Constants.NEWS_TABLE


@Entity(tableName = NEWS_TABLE)
data class NewsEntity (
    @PrimaryKey(autoGenerate = true)
    val newsId:Int,
    @ColumnInfo(name = "Title")
    var newsTitle:String,
    @ColumnInfo(name = "Description")
    var newsDesc:String,
    @ColumnInfo(name = "Source")
    var newsAuthor:String,
    @ColumnInfo(name = "Content")
    var newsContent:String,
    @ColumnInfo(name = "ImageUrl")
    var newsImage:String

)
/*
@Entity(tableName = "USER_TABLE")
data class User (
        @PrimaryKey(autoGenerate = true)
        val userId:Int,
        @ColumnInfo(name = "First Name")
        var firstName:String,
        @ColumnInfo(name = "Last Name")
        var lastName:String,
        @ColumnInfo(name = "Email")
        var email:String,
        @ColumnInfo(name = "Age")
        var age:Int
        )
 */