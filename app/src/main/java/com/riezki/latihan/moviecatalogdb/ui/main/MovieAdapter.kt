package com.riezki.latihan.moviecatalogdb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.callback.MovieCallback
import com.riezki.latihan.moviecatalogdb.databinding.ItemFragmentBinding
import com.riezki.latihan.moviecatalogdb.model.MovieItems

class MovieAdapter(private val callback: MovieCallback) : RecyclerView.Adapter<MovieAdapter.MovieTvViewHolder>() {
    private val listMovies = ArrayList<MovieItems>()

    fun setList(movieItems: ArrayList<MovieItems>) {
        this.listMovies.clear()
        this.listMovies.addAll(movieItems)
        notifyDataSetChanged()
        //notifyItemChanged(listMovies.size)
    }

    inner class MovieTvViewHolder(private val binding: ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItems: MovieItems) {
            with(binding) {
                Glide.with(itemView)
                    .load(Companion.IMAGE_BASE + movieItems.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
                    .error(R.drawable.ic_baseline_error_outline)
                    .into(imgMovieTv)

                binding.txtName.text = movieItems.title
                binding.itemDate.text = movieItems.releaseDate
                binding.rating.text = movieItems.voteAverage.toString()
                binding.description.text = movieItems.overview

                binding.imgShare.setOnClickListener { callback.onShareClick(movieItems) }
                itemView.setOnClickListener { callback.toDetail(movieItems) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieTvViewHolder {
        val itemFragmentBinding = ItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieTvViewHolder(itemFragmentBinding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieTvViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    companion object {
        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    }
}