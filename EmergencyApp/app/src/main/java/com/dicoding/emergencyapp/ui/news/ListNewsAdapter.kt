package com.dicoding.emergencyapp.ui.news

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.databinding.ItemNewsBinding
import com.dicoding.emergencyapp.helpers.DateHelper

class ListNewsAdapter: RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {

    private var listData = ArrayList<ArticlesItem?>()
    fun setData(list: ArrayList<ArticlesItem?>){
        this.listData = list
        notifyDataSetChanged()
    }

    fun clearData(){
        this.listData.clear()
    }

    private var category: String? = null
    fun setCategory(category: String){
        this.category = category
    }

    inner class ListViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesItem?){
            with(binding){
                val titleAndSource = getTitleAndSource(article?.title)
                val title = titleAndSource?.get(0)
                val source = titleAndSource?.get(1)
                tvNewsTitle.text = title
                tvHoursAgo.text = DateHelper.cleanDate(article?.publishedAt)
                tvSource.text = source
                if (article?.urlToImage == null){
                    imgNews.setImageResource(R.drawable.ic_broken_image)
                } else {
                    Glide.with(itemView.context)
                            .load(article?.urlToImage)
                            .apply(RequestOptions().override(500,500))
                            .into(imgNews)
                }
            }
            itemView.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(article?.url)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    private fun getTitleAndSource(title: String?): List<String>? {
        val result = title?.split(" - ")
        return result
    }
}