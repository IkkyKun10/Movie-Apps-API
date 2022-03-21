package com.riezki.latihan.moviecatalogdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.riezki.latihan.moviecatalogdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.elevation = 0f

//        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
//        binding.pager.adapter = sectionPagerAdapter
        //binding.tabLayout.setupWithViewPager(binding.pager)
    }

}