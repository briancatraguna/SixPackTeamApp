package com.dicoding.emergencyapp.ui.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.emergencyapp.data.entity.ArticlesItem
import com.dicoding.emergencyapp.databinding.ItemNewsBinding
import com.dicoding.emergencyapp.ui.news.detail.DetailNewsActivity

class ListNewsAdapter: RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {

    private var listData: List<ArticlesItem?> = ArrayList<ArticlesItem>()
    fun setData(list: List<ArticlesItem?>){
        this.listData = list
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesItem?){
            with(binding){
                val titleAndSource = getTitleAndSource(article?.title)
                val title = titleAndSource?.get(0)
                val source = titleAndSource?.get(1)
                tvNewsTitle.text = title
                tvHoursAgo.text = article?.publishedAt
                tvSource.text = source
                Glide.with(itemView.context)
                    .load(article?.urlToImage)
                    .apply(RequestOptions().override(500,500))
                    .into(imgNews)
            }
            itemView.setOnClickListener{
                val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                intent.putExtra(DetailNewsActivity.EXTRA_AUTHOR,article?.author)
                intent.putExtra(DetailNewsActivity.EXTRA_DESCRIPTION,article?.description)
                intent.putExtra(DetailNewsActivity.EXTRA_PUBLISHED,article?.publishedAt)
                intent.putExtra(DetailNewsActivity.EXTRA_SOURCE,article?.source?.name)
                intent.putExtra(DetailNewsActivity.EXTRA_CONTENT,article?.content)
                intent.putExtra(DetailNewsActivity.EXTRA_TITLE,article?.title)
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