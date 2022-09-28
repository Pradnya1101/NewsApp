package com.example.newsapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewsAdapter(val newsItemClicked: MainActivity, val context: Context, val articles: List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> (){

    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article:Article=articles[position]
        holder.newsTitle.text=article.title
        holder.newsDescription.text=article.description
        holder.newsAuthor.text=article.author
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.newsImage)
        holder.layout.setOnClickListener{
            newsItemClicked.onItemClicked(article)
        }
        holder.sharebtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Hello, Checkout the News \nNewsAPP( By Pradnya) \n${article.url} ")

            val chooser=Intent.createChooser(intent, "Share News via")
            context.startActivity(chooser)
        }

    }

    override fun getItemCount(): Int {
        return  articles.size
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var newsImage=itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle=itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription=itemView.findViewById<TextView>(R.id.newsDescription)
        var newsAuthor=itemView.findViewById<TextView>(R.id.newsAuthor)
        var sharebtn = itemView.findViewById<FloatingActionButton>(R.id.sharebtn)
        var layout=itemView.findViewById<LinearLayout>(R.id.layout)
    }


    interface NewsItemClicked{
        fun onItemClicked(item:Article)
        //fun search(item: Article)
    }

}