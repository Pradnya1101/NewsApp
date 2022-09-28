package com.example.newsapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapi.db.NewsDatabase
import com.example.newsapi.db.NewsEntity

class SavedReAdapter(val savednews: List<NewsEntity>,  val newsItemClicked: SavedActivity): RecyclerView.Adapter<SavedReAdapter.SavedNewsViewHolder> () {
    private var list = emptyList<News>()
    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_saved, parent, false)
        return SavedNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val currentItem = savednews[position]
        holder.newsTitle.text = currentItem.newsTitle
        holder.newsDescription.text = currentItem.newsDesc
        holder.newsAuthor.text = currentItem.newsAuthor
        Glide.with(holder.itemView.context).load(currentItem.newsImage).into(holder.newsImage)
        holder.layout.setOnClickListener{
            newsItemClicked.onItemClicked(savednews, position)
        }
        holder.delete.setOnClickListener {
            newsItemClicked.deleteclick(savednews,position)
        }
        holder.notify.setOnClickListener {
            newsItemClicked.notify(savednews,position)
        }
    }

    override fun getItemCount(): Int {
        return savednews.size
    }

    class SavedNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
        var newsAuthor = itemView.findViewById<TextView>(R.id.newsAuthor)
        var delete = itemView.findViewById<ImageButton>(R.id.delete_btn)
        var notify = itemView.findViewById<ImageButton>(R.id.notify)
        var layout = itemView.findViewById<LinearLayout>(R.id.layout)
    }


    interface NewsItemClicked {
        fun onItemClicked(item: List<NewsEntity>, position: Int)
        fun deleteclick(item: List<NewsEntity>, position: Int)
        fun notify(item: List<NewsEntity>, position: Int)
    }

}