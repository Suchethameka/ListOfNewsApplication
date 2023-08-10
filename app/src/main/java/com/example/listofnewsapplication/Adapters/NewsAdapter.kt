package com.example.listofnewsapplication.Adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listofnewsapplication.data.News
import com.example.listofnewsapplication.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso

class NewsAdapter(private var list: List<News>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    interface OnClickListener {
        fun onItemClick(newsItem: News)
        fun onSaveClick(newsItem: News)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onItemClick(list[position])
                }
            }

            binding.btnSave.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onSaveClick(list[position])
                }
            }
        }

        fun bind(data: News) {
            with(binding) {
                tvTitle.text = data.title
                tvAuthor.text = data.author
                tvPublished.text = data.published
                val categories = data.category.joinToString(", ")
                tvCategory.text = categories
                tvDescription.text = data.description
                Picasso.get().load(data.image).into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: List<News>) {
        list = newList
        notifyDataSetChanged()
    }

    fun updateSavedData(newsItem: News) {
        val newList = list.toMutableList()
        newList.add(newsItem)
        list = newList
        notifyItemInserted(list.size - 1)
    }
}


