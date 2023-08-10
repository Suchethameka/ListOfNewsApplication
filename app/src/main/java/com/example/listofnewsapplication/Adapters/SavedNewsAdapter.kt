package com.example.listofnewsapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listofnewsapplication.data.News
import com.example.listofnewsapplication.databinding.ItemSavedNewsBinding
import com.squareup.picasso.Picasso

class SavedNewsAdapter :
    ListAdapter<News, SavedNewsAdapter.SavedNewsViewHolder>(NewsDiffCallback()) {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(newsItem: News)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    class SavedNewsViewHolder(private val binding: ItemSavedNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
//                    onItemClickListener!!.onItemClick(getItem(position))
//                }
            }
        }

        fun bind(newsItem: News) {
            with(binding) {
                tvSavedTitle.text = newsItem.title
                tvSavedPublished.text = newsItem.published
                tvSavedDescription.text = newsItem.description
                tvSavedCategory.text = newsItem.category.joinToString(", ")
                tvSavedAuthor.text = newsItem.author

                Picasso.get().load(newsItem.image).into(savedImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSavedNewsBinding.inflate(layoutInflater, parent, false)
        return SavedNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(newsItem)
        }
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}


