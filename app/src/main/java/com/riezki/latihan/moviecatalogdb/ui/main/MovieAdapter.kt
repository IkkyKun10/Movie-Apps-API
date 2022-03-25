package com.riezki.latihan.moviecatalogdb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riezki.latihan.core.domain.model.Movies
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.ui.callback.MovieCallback
import com.riezki.latihan.moviecatalogdb.databinding.ItemFragmentBinding

class MovieAdapter(private val callback: MovieCallback) : RecyclerView.Adapter<MovieAdapter.MovieTvViewHolder>() {
    private val listMovies = ArrayList<Movies>()

    fun setList(movieItemResponses: List<Movies>?) {
        this.listMovies.clear()
        movieItemResponses?.let { this.listMovies.addAll(it) }
        notifyDataSetChanged()
        //notifyItemChanged(listMovies.size)
    }

    inner class MovieTvViewHolder(private val binding: ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies) {
            with(binding) {
                Glide.with(itemView)
                    .load(Companion.IMAGE_BASE + movies.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
                    .error(R.drawable.ic_baseline_error_outline)
                    .into(imgMovieTv)

                binding.txtName.text = movies.title
                binding.itemDate.text = movies.realeaseDate
                binding.rating.text = movies.voteAverage.toString()
                binding.description.text = movies.overview

                binding.imgShare.setOnClickListener { callback.onShareClick(movies) }
                itemView.setOnClickListener { callback.toDetail(movies) }
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