package com.example.listofnewsapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listofnewsapplication.Adapters.NewsAdapter
import com.example.listofnewsapplication.R
import com.example.listofnewsapplication.data.News
import com.example.listofnewsapplication.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: NewsAdapter
    private val searchResultsList = mutableListOf<News>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupSearchView()

        return binding.root
    }

    private fun setupRecyclerView() {
        searchAdapter = NewsAdapter(emptyList(), object : NewsAdapter.OnClickListener {
            override fun onItemClick(newsItem: News) {
                val detailsFragment = DetailsFragment.newInstance(newsItem)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, detailsFragment)
                    .addToBackStack(null)
                    .commit()
            }

            override fun onSaveClick(newsItem: News) {
                Toast.makeText(requireContext(), "News saved!", Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvSearchResults.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    val filteredList = searchResultsList.filter {
                        it.title.contains(query, true) || it.description.contains(query, true)
                    }
                    searchAdapter.updateData(filteredList)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    searchAdapter.updateData(searchResultsList)
                }
                return true
            }
        })
    }
}
