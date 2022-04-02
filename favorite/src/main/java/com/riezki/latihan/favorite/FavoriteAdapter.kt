package com.riezki.latihan.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riezki.latihan.core.domain.model.Movies
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.databinding.ItemFragmentBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListMovieAdapter>() {
    private val listMovies = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setList(listMovie: List<Movies>) {
        this.listMovies.clear()
        this.listMovies.addAll(listMovie)
        notifyDataSetChanged()
    }

    inner class ListMovieAdapter(private val binding: ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies) {
            with(binding) {
                Glide.with(itemView)
                    .load(IMAGE_BASE + movies.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
                    .error(R.drawable.ic_baseline_error_outline)
                    .into(imgMovieTv)

                binding.txtName.text = movies.title
                binding.itemDate.text = movies.realeaseDate
                binding.rating.text = movies.voteAverage.toString()
                binding.description.text = movies.overview
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listMovies[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieAdapter {
        val itemFragmentBinding = ItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListMovieAdapter(itemFragmentBinding)
    }

    override fun onBindViewHolder(holder: ListMovieAdapter, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    companion object {
        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    }
}