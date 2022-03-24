package com.riezki.latihan.moviecatalogdb.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.databinding.MainFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding?= null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAdapter

    private val viewModelFavorite: FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)

        showLoading(true)
        showRecycler()

        viewModelFavorite.favoriteMovie.observe(viewLifecycleOwner) { favorite ->
            if (favorite != null) {
                showLoading(false)
                adapter.setList(favorite)
            } else {
                showLoading(false)
                Toast.makeText(context, "Oops, Favorite Kosong", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.onItemClick = { favorite ->
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(favorite)
            findNavController().navigate(action)
        }
    }

    private fun showRecycler() {
        binding.rvMovieTv.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteAdapter()
        binding.rvMovieTv.adapter = adapter
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