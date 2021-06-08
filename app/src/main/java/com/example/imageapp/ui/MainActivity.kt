package com.example.imageapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.imageapp.R
import com.example.imageapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setSupportActionBar(binding.toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null){
            findNavController(R.id.fragment_main).addOnDestinationChangedListener{_, destination, _ ->
                menu.clear()
                if (destination.id == R.id.listImagesFragment){
                    menuInflater.inflate(R.menu.search_menu, menu)
                    val menuItem = menu.findItem(R.id.action_search)
                    val searchView = menuItem.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            if (query != null && query.isNotEmpty()){
                                viewModel.searchImages(query)
                            }
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            return true
                        }
                    })
                }

            }
        }
        return super.onCreateOptionsMenu(menu)
    }


}