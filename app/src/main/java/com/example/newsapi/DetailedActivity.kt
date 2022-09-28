package com.example.newsapi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newsapi.databinding.ActivityNewsBinding
import com.example.newsapi.db.NewsDatabase
import com.example.newsapi.db.NewsEntity
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.item_layout.*

class DetailedActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var newsEntity: NewsEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        lateinit var newsentity: List<NewsEntity>
        val dao = NewsDatabase.getAppDb(this)?.dao()
        newsentity = dao?.getNews()!!

        val title: TextView = findViewById(R.id.Title)
        val author: TextView = findViewById(R.id.author)
        val image: ImageView = findViewById(R.id.image_news)
        val desc: TextView = findViewById(R.id.Description)
        val cont: TextView = findViewById(R.id.content)

        /*
        val newstitle = newsEntity.newsTitle
        val newsauthor = newsEntity.newsAuthor
        //val newsimage = extras.getInt("newsImage")
        val newsdescription = newsEntity.newsDesc
        val newscontent = newsEntity.newsContent

        title.text = newstitle
        author.text = newsauthor
        //image.setImageResource(newsimage)
        desc.text = newsdescription
        cont.text = newscontent
        */

        Glide.with(this).load(intent.getStringExtra("newsimage")).into(image_news)
        val extras = intent.extras
        if (extras != null) {
            val newstitle = extras.getString("newstitle")
            val newsauthor = extras.getString("newsauthor")
            val newsimage = extras.getInt("newsimage")
            val newsdescription = extras.getString("newsdescription")
            val newscontent = extras.getString(("newscontent"))
            //The key argument here must match that used in the other activity
            title.text = newstitle
            author.text = newsauthor
            image.setImageResource(newsimage)
            desc.text = newsdescription
            cont.text = newscontent
        }

    }
}