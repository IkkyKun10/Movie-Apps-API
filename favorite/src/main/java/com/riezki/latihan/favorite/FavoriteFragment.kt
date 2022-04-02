package com.riezki.latihan.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.moviecatalogdb.databinding.FavoriteFragmentBinding
import com.riezki.latihan.moviecatalogdb.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment(R.layout.favorite_fragment) {

    private var _binding: FavoriteFragmentBinding?= null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAdapter

    private val viewModelFavorite: FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FavoriteFragmentBinding.bind(view)

        loadKoinModules(favoriteModule)

        showRecycler()

        viewModelFavorite.favoriteMovie.observe(viewLifecycleOwner) { favorite ->
            if (favorite != null) {
                adapter.setList(favorite)
            }

            val favEmpty = favorite.isEmpty()
            if (favEmpty) {
                binding.viewEmpty.root.visibility = View.VISIBLE
                Toast.makeText(context, "Favorite Empty", Toast.LENGTH_SHORT).show()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}