package com.riezki.latihan.moviecatalogdb.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.core.domain.model.Movies
import com.riezki.latihan.moviecatalogdb.databinding.ActivityDetailBinding
import com.riezki.latihan.moviecatalogdb.viewmodel.MovieDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val args by navArgs<DetailActivityArgs>()

    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModelMovie: MovieDetailViewModel by viewModel()
//    private val viewModelTv by viewModels<TvDetailViewModel>()
    private var stateFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val id = args.movieArgs.movieId
        val movie = args.movieArgs

        supportActionBar?.hide()
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

//        id.let {
//            viewModelMovie.getDetailMovie(id).observe(this@DetailActivity) { movies ->
//                if (movies != null) {
//                    when (movies) {
//                        is Resource.Loading -> detailBinding.progressBar.visibility = View.VISIBLE
//                        is Resource.Success -> {
//                            detailBinding.progressBar.visibility = View.GONE
//                            showMovieDetailResponse(movies.data)
//                            supportActionBar?.title = movies.data?.title
//                        }
//                        is Resource.Error -> {
//                            detailBinding.progressBar.visibility = View.GONE
//                            Toast.makeText(this, "Opps, Something Wrong", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//
//
//                }
//            }
//            viewModelMovie.getDetailGenre(id).observe(this@DetailActivity) { genre ->
//                if (genre != null) {
//                    when (genre) {
//                        is Resource.Loading -> detailBinding.progressBar.visibility = View.VISIBLE
//                        is Resource.Success -> {
//                            detailBinding.progressBar.visibility = View.GONE
//                            showMovieGenreDetail(genre.data)
//                        }
//                        is Resource.Error -> {
//                            detailBinding.progressBar.visibility = View.GONE
//                            Toast.makeText(this, "Opps, Something Wrong", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//
//                }
//            }
//            viewModelMovie.getCompany(id).observe(this@DetailActivity) { company ->
//                if (company != null) {
//                    when (company) {
//                        is Resource.Loading -> detailBinding.progressBar.visibility = View.VISIBLE
//                        is Resource.Success -> {
//                            detailBinding.progressBar.visibility = View.GONE
//                            showMovieCompany(company.data)
//                        }
//                        is Resource.Error -> {
//                            detailBinding.progressBar.visibility = View.GONE
//                            Toast.makeText(this, "Opps, Something Wrong", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//
//                }
//            }
//        }

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
//      detailBinding.txtTagline.text = movies?.tagline
        detailBinding.date.text = movies?.realeaseDate
        detailBinding.popularity.text = "Informasi Tidak Tersedia"
        detailBinding.userScore.text = movies?.voteAverage.toString()
//      detailBinding.txtBudget.text = this.resources.getString(R.string.budget, movies?.budget.toString())
//      detailBinding.txtSutradara.text = R.string.none.toString()

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
            detailBinding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            detailBinding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

//    private fun showMovieGenreDetail(genre: List<DomainGenresItem>?) {
//        detailBinding.txtGenre.text = genre?.get(0)?.name
//    }
//
//    private fun showMovieCompany(company: List<DomainProductionCompaniesItem>?) {
//        detailBinding.txtProduction.text = company?.get(0)?.name
//    }

    /**
    private fun showTvDetailResponse(tvShow: DetailTvResponse?) {
        detailBinding.txtTitle.text = tvShow?.name
        detailBinding.txtDesc.text = tvShow?.overview
        detailBinding.txtDate.text = tvShow?.firstAirDate
        detailBinding.txtTagline.text = tvShow?.tagline
        detailBinding.txtBudget.text = R.string.none.toString()

        Glide.with(this)
            .load(BASE_URL_IMAGE + tvShow?.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
            .error(R.drawable.ic_baseline_error_outline)
            .into(detailBinding.poster)

        Glide.with(this)
            .load(BASE_URL_IMAGE + tvShow?.backdropPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
            .error(R.drawable.ic_baseline_error_outline)
            .into(detailBinding.bgPoster)
    }

    private fun showTvGenreDetail(genre: List<GenresItemTv?>?) {
        //arrayListOf kemudian forEach{} & array kosong biasa ex: genre[0].toString()
        detailBinding.txtGenre.text = if (genre?.size!! > 0) genre[0]?.name.toString() else "Informasi tidak ada"
    }

    private fun showTvCompany(company: List<ProductionCompaniesItemTv?>?) {
        detailBinding.txtProduction.text = if (company?.size!! > 0) company.get(0)?.name.toString() else "Informasi tidak ada"
    }

    private fun createdBy(sutradara: List<CreatedByItem?>?) {
        detailBinding.txtSutradara.text = if (sutradara?.size!! > 0) sutradara.get(0)?.name.toString() else "Informasi tidak ada"
    }
    **/


    companion object {
        const val MOVIE_ID = "movie_id"
        const val TV_ID = "tv_id"

        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    }
}