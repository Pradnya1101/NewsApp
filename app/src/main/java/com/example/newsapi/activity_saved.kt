package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.newsapi.databinding.ActivityNewsBinding
import com.example.newsapi.db.NewsDatabase

class activity_saved : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //read data
        val dao = NewsDatabase.getAppDb(this)?.dao()
        val list = dao?.getNews()

        val title: TextView = findViewById(R.id.Title)
        val author: TextView = findViewById(R.id.author)
        val image: ImageView = findViewById(R.id.image_news)
        val desc: TextView = findViewById(R.id.Description)
        val cont: TextView = findViewById(R.id.content)

        if (list != null){
            list.newsTitle = title.toString()
            list.newsAuthor = author.toString()
            list.newsDesc = desc.toString()
            list.newsContent = cont.toString()

        }
    }*/
}