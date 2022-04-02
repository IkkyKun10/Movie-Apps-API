package com.riezki.latihan.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.databinding.MainFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding?= null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAdapter

    private val viewModelFavorite: FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)

        loadKoinModules(favoriteModule)

        showRecycler()

        viewModelFavorite.favoriteMovie.observe(viewLifecycleOwner) { favorite ->
            if (favorite != null) {
                adapter.setList(favorite)
            } else {
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
//
//    private fun showLoading(isLoading: Boolean) {
//        if (isLoading){
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.progressBar.visibility = View.GONE
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}