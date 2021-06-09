package com.example.imageapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.imageapp.R
import com.example.imageapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setSupportActionBar(binding.toolbarMain)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null){
            navController.addOnDestinationChangedListener { _, destination, _ ->
                menu.clear()
                when (destination.id) {
                    R.id.listImagesFragment -> {
                        menuInflater.inflate(R.menu.search_menu, menu)
                        val menuItem = menu.findItem(R.id.action_search)
                        val searchView = menuItem.actionView as SearchView
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                if (query != null && query.isNotEmpty()) {
                                    setLoading(true)
                                    viewModel.searchImages(query)
                                }
                                return true
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                return true
                            }
                        })
                    }
                    R.id.fullImagesFragment -> {
                        menuInflater.inflate(R.menu.source_menu, menu)
                    }
                }
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.listImagesFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (navController.currentDestination?.id) {
            R.id.fullImagesFragment -> {
                when (item.itemId) {
                    R.id.action_source -> navController.navigate(
                        R.id.action_fullImagesFragment_to_imageSourceFragment
                    )
                }
            }
        }
        return false
    }

    fun setLoading(value: Boolean) {
        if (value) {
            binding.loadScreen.root.visibility = View.VISIBLE
        } else {
            binding.loadScreen.root.visibility = View.GONE
        }
    }

}