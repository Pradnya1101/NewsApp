package com.example.newsapi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.example.newsapi.databinding.ActivityNewsBinding
import com.example.newsapi.db.NewsDatabase
import com.example.newsapi.db.NewsEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_saved.*
import java.util.concurrent.TimeUnit

class SavedActivity: AppCompatActivity(), SavedReAdapter.NewsItemClicked {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var sAdapter: SavedReAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var newsentity: List<NewsEntity>
        //read data
        val dao = NewsDatabase.getAppDb(this)?.dao()
        newsentity = dao?.getNews()!!
        //getSavedNews(newsentity)
        newsList.apply {
            layoutManager = LinearLayoutManager(this@SavedActivity)
            adapter = SavedReAdapter(newsentity, this@SavedActivity)
        }
        /*
        val i = StringBuffer()
        list?.forEach {
            i.append(it.toString())
        }
        title.text = i.toString() */

    }



    override fun onItemClicked(item: List<NewsEntity>, position: Int) {
        val item = item[position]
        val intent= Intent(this@SavedActivity,DetailedActivity::class.java)
        intent.putExtra("newsimage", item.newsImage)
        intent.putExtra("newstitle",item.newsTitle)
        intent.putExtra("newsauthor",item.newsAuthor)
        intent.putExtra("newsdescription",item.newsDesc)
        intent.putExtra("newscontent",item.newsContent)
        startActivity(intent)
    }

    override fun deleteclick(item: List<NewsEntity>, position: Int) {
        val item = item[position]
        val dao = NewsDatabase.getAppDb(this@SavedActivity)?.dao()
        dao?.deleteNews(item)
        Toast.makeText(this,"News is deleted",Toast.LENGTH_LONG).show()
    }

    override fun notify(item: List<NewsEntity>, position: Int) {
        periodicwork()
        Toast.makeText(this,"Reminder is set", Toast.LENGTH_LONG).show()
        //onetime()
    }

    private fun onetime() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(true)
            .build()

        val workRequest:WorkRequest = OneTimeWorkRequest.Builder(Worker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    //@SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun periodicwork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(Worker::class.java, 60, TimeUnit.MINUTES).setConstraints(constraints)
            .addTag("My_ID")
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork("My_ID", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }


}
