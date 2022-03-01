package com.riezki.latihan.moviecatalogdb.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.callback.TvCallback
import com.riezki.latihan.moviecatalogdb.databinding.MainFragmentBinding
import com.riezki.latihan.moviecatalogdb.model.TvShowItems
import com.riezki.latihan.moviecatalogdb.ui.detail.DetailActivity
import com.riezki.latihan.moviecatalogdb.viewmodel.TvShowViewModel

class TvFragment : Fragment(R.layout.main_fragment), TvCallback {

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
        tAdapter = TvShowAdapter(this)
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

    override fun onShareClick(tvShow: TvShowItems) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan Judul ini sekarang.")
                .setText("Segera tonton ${tvShow.originalName} di situs streaming langganan anda")
                .startChooser()
        }
    }

    override fun toDetail(tvShow: TvShowItems) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.TV_ID, tvShow.id)
        startActivity(intent)
    }
}