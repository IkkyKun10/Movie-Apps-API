package com.riezki.latihan.moviecatalogdb.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.databinding.MainFragmentBinding
import com.riezki.latihan.moviecatalogdb.viewmodel.TvShowViewModel

class TvFragment : Fragment(R.layout.main_fragment) {

    private var _binding : MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var tAdapter: TvShowAdapter
    private val viewModel by viewModels<TvShowViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)

        showRecylerView()
        showLoading(true)
        viewModel.setListTvShow()
        viewModel.getListTvShow().observe(viewLifecycleOwner, {tvShow ->
            if (tvShow != null) {
                tAdapter.setList(tvShow)
                showLoading(false)
            }
        })

    }

    private fun showRecylerView() {
        binding.rvMovieTv.layoutManager = LinearLayoutManager(context)
        tAdapter = TvShowAdapter()
        binding.rvMovieTv.adapter = tAdapter
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