package com.riezki.latihan.moviecatalogdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.riezki.latihan.moviecatalogdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.elevation = 0f

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var title = getString(R.string.app_name)
        when (item.itemId) {
            R.id.favorite -> {
                findNavController(R.id.fragment_main_navigation).navigate(R.id.action_movieFragment_to_favoriteFragment)
                title = getString(R.string.favorite)
            }
        }
        supportActionBar?.title = title
        return super.onOptionsItemSelected(item)
    }

}