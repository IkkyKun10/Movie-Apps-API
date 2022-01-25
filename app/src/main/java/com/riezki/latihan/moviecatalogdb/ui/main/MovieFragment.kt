package com.riezki.latihan.moviecatalogdb.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.databinding.MainFragmentBinding
import com.riezki.latihan.moviecatalogdb.viewmodel.MovieViewModel

class MovieFragment : Fragment(R.layout.main_fragment) {

    private var _binding : MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: MovieAdapter
    private val viewModel by viewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)

        showRecylerView()
        showLoading(true)
        viewModel.setListMovie()
        viewModel.getListMovies().observe(viewLifecycleOwner, {movies ->
            if (movies != null) {
                mAdapter.setList(movies)
                showLoading(false)
            }
        })
    }

    private fun showRecylerView() {
        binding.rvMovieTv.layoutManager = LinearLayoutManager(context)
        mAdapter = MovieAdapter()
        binding.rvMovieTv.adapter = mAdapter
        binding.rvMovieTv.setHasFixedSize(true)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}