package com.example.listofnewsapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.listofnewsapplication.R
import com.example.listofnewsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.setOnSearchClickListener {

        }

        return true
    }

    private fun initViews() {
        binding.bottomAppBar.setOnItemSelectedListener { menuItems ->
            menuItems.isChecked = true
            when (menuItems.itemId) {
                R.id.menu_home -> {
                    handleMenuEvent(HomeFragment())
                    setToolbarTitle("Home")
                }
                R.id.menu_saved -> {
                    handleMenuEvent(SavedFragment())
                    setToolbarTitle("Saved")
                }
                R.id.menu_accounts -> {
                    handleMenuEvent(AccountFragment())
                    setToolbarTitle("Accounts")
                }
            }
            true
        }
    }

    private fun handleMenuEvent(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}



