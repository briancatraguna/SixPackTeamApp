package com.dicoding.emergencyapp.ui.news.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.emergencyapp.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_AUTHOR = "extra_author"
        const val EXTRA_CONTENT = "extra_content"
        const val EXTRA_SOURCE = "extra_source"
        const val EXTRA_PUBLISHED = "extra_published"
        const val EXTRA_CATEGORY = "extra_category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populateView()
    }

    private fun populateView() {
        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val author = intent.getStringExtra(EXTRA_AUTHOR)
        val content = intent.getStringExtra(EXTRA_CONTENT)
        val source = intent.getStringExtra(EXTRA_SOURCE)
        val publishDate = intent.getStringExtra(EXTRA_PUBLISHED)
        val category = intent.getStringExtra(EXTRA_CATEGORY)

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        binding.tvAuthor.text = author
        binding.tvContent.text = content
        binding.tvSource.text = source
        binding.tvTime.text = publishDate
        binding.tvCategory.text = category
    }
}