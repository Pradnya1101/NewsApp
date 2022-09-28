package com.example.newsapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.databinding.ActivityNewsBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() ,NewsAdapter.NewsItemClicked{
    private lateinit var binding: ActivityNewsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: NewsAdapter
    private lateinit var tadapter: NewsAdapter
    private val news= ArrayList<Article>()
    val newsA= ArrayList<News>()
    private lateinit var temparr: ArrayList<News>
    lateinit var title : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.fav -> startActivity(Intent(this@MainActivity, SavedActivity::class.java))
            R.id.search_bar -> callsearch(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun callsearch(item: MenuItem) {
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(query: String?): Boolean {
               return false
           }

          override fun onQueryTextChange(newText: String?): Boolean {
              return true
           }

       })
    }


    private fun getNews() {
        val news:Call<News> = NewService.newsInstance.getHeadLines("in",1,"general")
        news.enqueue(object: Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val newsArray= ArrayList<News>()
               val news:News?= response.body()
                //Log.d("newsdata",news.toString())
                if(news!=null){
                    mAdapter= NewsAdapter(this@MainActivity,this@MainActivity,news.articles)
                    newsList.adapter=mAdapter
                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            }


            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error","Error"+t.message)
            }

        })

    }


    override fun onItemClicked(item: Article) {

        val intent=Intent(this@MainActivity,NewsActivity::class.java)

        intent.putExtra("newsimage",item.urlToImage)
        intent.putExtra("newstitle",item.title)
        intent.putExtra("newsauthor",item.author)
        intent.putExtra("newsdescription",item.description)
        intent.putExtra("newscontent",item.content)
        intent.putExtra("newsimageurl", item.urlToImage)
        startActivity(intent)
    }

}



