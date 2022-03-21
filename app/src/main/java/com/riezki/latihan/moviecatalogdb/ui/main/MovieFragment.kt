package com.riezki.latihan.moviecatalogdb.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.callback.MovieCallback
import com.riezki.latihan.moviecatalogdb.databinding.MainFragmentBinding
import com.riezki.latihan.moviecatalogdb.model.DetailMovieResponse
import com.riezki.latihan.moviecatalogdb.model.MovieItems
import com.riezki.latihan.moviecatalogdb.ui.detail.DetailActivity
import com.riezki.latihan.moviecatalogdb.viewmodel.MovieViewModel

class MovieFragment : Fragment(R.layout.main_fragment), MovieCallback {

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
        viewModel.getListMovies().observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                mAdapter.setList(movies)
                showLoading(false)
            }
        }
    }

    private fun showRecylerView() {
        binding.rvMovieTv.layoutManager = LinearLayoutManager(context)
        mAdapter = MovieAdapter(this)
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

    override fun onShareClick(movie: MovieItems) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan Judul ini sekarang.")
                .setText("Segera tonton ${movie.originalTitle} di bioskop terdekat anda")
                .startChooser()
        }
    }

    override fun toDetail(movie: MovieItems) {
//        val intent = Intent(requireActivity(), DetailActivity::class.java)
//        intent.putExtra(DetailActivity.MOVIE_ID, movie.id)
//        startActivity(intent)
        val action = MovieFragmentDirections.actionMovieFragmentToDetailActivity(movie.id)
        findNavController().navigate(action)
    }
}