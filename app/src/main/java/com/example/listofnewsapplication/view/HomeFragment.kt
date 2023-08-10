package com.example.listofnewsapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.listofnewsapplication.R
import com.example.listofnewsapplication.model.VolleyHandler
import com.example.listofnewsapplication.data.DataBaseHelper
import com.example.listofnewsapplication.databinding.FragmentHomeBinding
import com.example.listofnewsapplication.model.NewsRepository
import com.example.listofnewsapplication.data.News
import com.example.listofnewsapplication.Adapters.NewsAdapter
import com.example.listofnewsapplication.data.NewsResponse
import com.example.listofnewsapplication.presenter.NewsPresenter
import com.google.gson.Gson

class HomeFragment : Fragment(), NewsView {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var requestQueue: RequestQueue
    private lateinit var adapter: NewsAdapter
    private lateinit var presenter: NewsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        requestQueue = Volley.newRequestQueue(requireContext())
        val databaseHelper = DataBaseHelper(requireContext())
        val newsRepository = NewsRepository(requireContext(), databaseHelper)
        presenter = NewsPresenter(this, newsRepository)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(emptyList(), object : NewsAdapter.OnClickListener {
            override fun onItemClick(newsItem: News) {
                openDetailsFragment(newsItem)
            }

            private fun openDetailsFragment(newsItem: News) {
                val detailsFragment = DetailsFragment.newInstance(newsItem)
                handleMenuEvent(detailsFragment)
            }
            private fun handleMenuEvent(fragment: Fragment) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            override fun onSaveClick(newsItem: News) {
                presenter.onSaveClick(newsItem)
            }
        })
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.apply {
            queryHint = "Search by category"

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        presenter.searchNews(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Implement any filtering as needed
                    return true
                }
            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).setToolbarTitle("Home")
        sendGetTypeRequest()
    }
    private fun sendGetTypeRequest() {
        val stringRequest = object : StringRequest(
            Request.Method.GET,
            VolleyHandler.BASE_URL_NEWS,
            { response ->
                Log.i("tag", "$response")

                val newsResponse = Gson().fromJson(response, NewsResponse::class.java)
                if (newsResponse.status == "ok") {
                    presenter.fetchNews()
                }
            },
            { error ->
                Log.i("tag", error.toString())
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "mNnL1N3VFziPVP8w29dxLQGmA7YBASQsorxyBUoELjyYUR7U"
                return headers
            }
        }

        requestQueue.add(stringRequest)
    }

    override fun showNews(newsList: List<News>) {
        adapter.updateData(newsList)
    }

    override fun showSearchResults(searchResults: List<News>) {
        adapter.updateData(searchResults)
    }

    fun updateSavedData(newsItem: News) {
        adapter.updateSavedData(newsItem)
    }
}


