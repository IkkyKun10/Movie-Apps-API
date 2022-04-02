package com.riezki.latihan.moviecatalogdb.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.core.data.Resource
import com.riezki.latihan.core.domain.model.Movies
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.ui.callback.MovieCallback
import com.riezki.latihan.moviecatalogdb.databinding.MainFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(R.layout.main_fragment), MovieCallback {

    private var _binding : MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: MovieAdapter

    private val viewModel: MovieViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)

        showRecylerView()

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        mAdapter.setList(movies.data)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        binding.viewError.root.visibility = View.VISIBLE
                        Toast.makeText(context, "Opps, Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
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

    override fun onShareClick(movieResponse: Movies) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan Judul ini sekarang.")
                .setText("Segera tonton ${movieResponse.title} di bioskop terdekat anda")
                .startChooser()
        }
    }

    override fun toDetail(movieResponse: Movies) {
        val action = MovieFragmentDirections.actionMovieFragmentToDetailActivity(movieResponse)
        findNavController().navigate(action)
    }
}