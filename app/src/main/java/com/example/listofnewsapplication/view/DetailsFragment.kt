package com.example.listofnewsapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.listofnewsapplication.databinding.FragmentDetailsBinding
import com.example.listofnewsapplication.data.News
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var newsItem: News


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = arguments?.getString(ARG_IMAGE)
        if (!imageUrl. isNullOrEmpty()){
            Picasso.get().load(imageUrl).into(binding.imgDetails)
        }

        binding.apply {
            tvDetailsTitle.text = arguments?.getString(ARG_TITLE)
            tvDetailsPublished.text = arguments?.getString(ARG_PUBLISHED)
            tvDetailsDescription.text = arguments?.getString(ARG_DESCRIPTION)
            tvDetailsCategory.text = arguments?.getString(ARG_CATEGORY)
            tvDetailsAuthor.text = arguments?.getString(ARG_AUTHOR)
        }

    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_PUBLISHED = "published"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_CATEGORY = "category"
        private const val ARG_AUTHOR = "author"
        private const val ARG_IMAGE = "image"

        fun newInstance(newsItem: News): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, newsItem.title)
                    putString(ARG_PUBLISHED, newsItem.published)
                    putString(ARG_DESCRIPTION, newsItem.description)
                    putString(ARG_CATEGORY, newsItem.category.joinToString(", "))
                    putString(ARG_AUTHOR, newsItem.author)
                    putString(ARG_IMAGE, newsItem.image)
                }
            }
        }
    }
}
