/**
package com.riezki.latihan.moviecatalogdb.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.callback.TvCallback
import com.riezki.latihan.moviecatalogdb.databinding.ItemFragmentBinding
import com.riezki.latihan.moviecatalogdb.model.TvShowItems

class TvShowAdapter(private val callback: TvCallback) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    private val listTvShow = ArrayList<TvShowItems>()

    fun setList(tvShowItems: ArrayList<TvShowItems>) {
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShowItems)
        notifyDataSetChanged()
    }

    inner class TvShowViewHolder(private val binding: ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowItems: TvShowItems) {
            with(binding) {
                Glide.with(itemView)
                    .load(IMAGE_BASE + tvShowItems.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
                    .error(R.drawable.ic_baseline_error_outline)
                    .into(binding.imgMovieTv)

                binding.txtName.text = tvShowItems.name
                binding.rating.text = tvShowItems.voteAverage.toString()
                binding.itemDate.text = tvShowItems.firstAirDate
                binding.description.text = tvShowItems.overview

                binding.imgShare.setOnClickListener { callback.onShareClick(tvShowItems) }
                itemView.setOnClickListener { callback.toDetail(tvShowItems) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.TvShowViewHolder {
        val itemFragmentBinding = ItemFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemFragmentBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size
}
 **/