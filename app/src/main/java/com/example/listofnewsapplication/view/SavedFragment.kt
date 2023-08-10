package com.example.listofnewsapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listofnewsapplication.Adapters.SavedNewsAdapter
import com.example.listofnewsapplication.R
import com.example.listofnewsapplication.data.DataBaseHelper
import com.example.listofnewsapplication.data.News
import com.example.listofnewsapplication.data.SavedNewsRepository
import com.example.listofnewsapplication.databinding.FragmentSavedBinding
import com.example.listofnewsapplication.presenter.SavedPresenter
import com.example.listofnewsapplication.presenter.SavedPresenterImpl

class SavedFragment : Fragment(), SavedView {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var savedNewsAdapter: SavedNewsAdapter
    private lateinit var presenter: SavedPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val databaseHelper = DataBaseHelper(requireContext())
        val repository = SavedNewsRepository(databaseHelper)
        presenter = SavedPresenterImpl(this, repository)

        savedNewsAdapter = SavedNewsAdapter()
        savedNewsAdapter.setOnItemClickListener(object : SavedNewsAdapter.OnItemClickListener {
            override fun onItemClick(newsItem: News) {
                openDetailsFragment(newsItem)
            }
        })

        binding.rvSavedNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = savedNewsAdapter
        }

        presenter.loadSavedNews()
    }

    override fun showSavedNews(newsList: List<News>) {
        savedNewsAdapter.submitList(newsList)
    }

    override fun onNewsItemSaved() {
        Toast.makeText(requireContext(), "News item saved!", Toast.LENGTH_SHORT).show()
        presenter.loadSavedNews()
    }

    private fun openDetailsFragment(newsItem: News) {
        val detailsFragment = DetailsFragment.newInstance(newsItem)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
}




