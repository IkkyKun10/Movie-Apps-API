package com.riezki.latihan.moviecatalogdb.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riezki.latihan.core.domain.model.Movies
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val args by navArgs<DetailActivityArgs>()

    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModelMovie: MovieDetailViewModel by viewModel()
    private var stateFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val id = args.movieArgs.movieId
        val movie = args.movieArgs

        supportActionBar?.hide()

        supportActionBar?.elevation = 0f

        showMovieDetailResponse(movie)
        detailBinding.backButton.setOnClickListener { onBackPressed() }

        stateFavorite = movie.isFavorite
        setStatusFavorite(stateFavorite)
        detailBinding.favoriteButton.setOnClickListener {
            stateFavorite = !stateFavorite
            viewModelMovie.setFavoriteMovie(movie, stateFavorite)
            setStatusFavorite(stateFavorite)
        }
    }

    private fun showMovieDetailResponse(movies: Movies?) {
        detailBinding.overview.text = movies?.overview
        detailBinding.titleDetail.text = movies?.title
        detailBinding.date.text = movies?.realeaseDate
        detailBinding.popularity.text = getString(R.string.info_not_resource)
        detailBinding.userScore.text = movies?.voteAverage.toString()

        Glide.with(this)
            .load(BASE_URL_IMAGE + movies?.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
            .error(R.drawable.ic_baseline_error_outline)
            .into(detailBinding.subPoster)

        Glide.with(this)
            .load(BASE_URL_IMAGE + movies?.backdropPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
            .error(R.drawable.ic_baseline_error_outline)
            .into(detailBinding.posterTopBar)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            detailBinding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_new_100))
        } else {
            detailBinding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    companion object {
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    }
}