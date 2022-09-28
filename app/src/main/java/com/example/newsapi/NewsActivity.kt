package com.example.newsapi


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.work.*
import com.bumptech.glide.Glide
import com.example.newsapi.databinding.ActivityNewsBinding
import com.example.newsapi.db.NewsDatabase
import com.example.newsapi.db.NewsEntity
import com.example.newsapi.utils.Constants
import com.example.newsapi.utils.Constants.NEWS_DATABASE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity:AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var newsEntity: NewsEntity
    /*private val newsDB: NewsDatabase by lazy { //instance
        Room.databaseBuilder(this, NewsDatabase::class.java, Constants.NEWS_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title: TextView = findViewById(R.id.Title)
        val author: TextView = findViewById(R.id.author)
        val image: ImageView = findViewById(R.id.image_news)
        val desc: TextView = findViewById(R.id.Description)
        val cont: TextView = findViewById(R.id.content)

        Glide.with(this).load(intent.getStringExtra("newsimage")).into(image_news)


        val extras = intent.extras
        if (extras != null) {
            val newstitle = extras.getString("newstitle")
            val newsauthor = extras.getString("newsauthor")
            val newsimage = extras.getInt("newsimage")
            val imgurl = extras.getString("newsimageurl")
            val newsdescription = extras.getString("newsdescription")
            val newscontent = extras.getString(("newscontent"))
            //The key argument here must match that used in the other activity
            title.text = newstitle
            author.text = newsauthor
            image.setImageResource(newsimage)
            desc.text = newsdescription
            cont.text = newscontent

            binding.saveBtn.setOnClickListener {
                val title = Title.text.toString()
                val desc = Description.text.toString()
                val author = author.text.toString()
                val content = content.text.toString()
                val url = imgurl.toString()

                if (title.isNotEmpty()) {
                    newsEntity = NewsEntity(0, title, desc, author, content, url)
                    val dao = NewsDatabase.getAppDb(this@NewsActivity)?.dao()
                    dao?.insertnews(newsEntity)
                    onetime()
                    Toast.makeText(this,"News is saved",Toast.LENGTH_LONG).show()
                    //startActivity(Intent(this@NewsActivity, SavedActivity::class.java))
                    // finish()

                } else {
                    Toast.makeText(this@NewsActivity, "No news to save", Toast.LENGTH_LONG).show()
                }
            }


            }
        }

    private fun onetime() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(true)
            .build()

        val workRequest: WorkRequest = OneTimeWorkRequest.Builder(Worker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
    /* cbHeart.setOnCheckedChangeListener { checkBox, isChecked ->

    if (isChecked) {

        Toast.makeText(this, "Item added to SaveList", Toast.LENGTH_SHORT).show()

    } else {
        Toast.makeText(this, "Item removed from Savedlist", Toast.LENGTH_SHORT).show()
    }

} */
    }




