package com.riezki.latihan.moviecatalogdb.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.riezki.latihan.moviecatalogdb.R
import com.riezki.latihan.moviecatalogdb.databinding.ActivityDetailBinding
import com.riezki.latihan.moviecatalogdb.model.*
import com.riezki.latihan.moviecatalogdb.viewmodel.MovieDetailViewModel

class DetailActivity : AppCompatActivity() {

    private val args by navArgs<DetailActivityArgs>()

    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModelMovie by viewModels<MovieDetailViewModel>()
//    private val viewModelTv by viewModels<TvDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val id = args.movieArgs

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        id.let {
            detailBinding.progressBar.visibility = View.VISIBLE
            viewModelMovie.setDetailMovie(it)
            viewModelMovie.getDetailResponse().observe(this@DetailActivity) { movies ->
                if (movies != null) {
                    detailBinding.progressBar.visibility = View.GONE
                    showMovieDetailResponse(movies)
                    supportActionBar?.title = movies.title
                }
            }
            viewModelMovie.getGenre().observe(this@DetailActivity) { genre ->
                if (genre != null) {
                    detailBinding.progressBar.visibility = View.GONE
                    showMovieGenreDetail(genre)

                }
            }
            viewModelMovie.getCompanyItem().observe(this@DetailActivity) { company ->
                if (company != null) {
                    detailBinding.progressBar.visibility = View.GONE
                    showMovieCompany(company)

                }
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    private fun showMovieDetailResponse(movies: DetailMovieResponse?) {
        detailBinding.txtDesc.text = movies?.overview
        detailBinding.txtTitle.text = movies?.title
        detailBinding.txtTagline.text = movies?.tagline
        detailBinding.txtDate.text = movies?.releaseDate
        detailBinding.txtBudget.text = this.resources.getString(R.string.budget, movies?.budget.toString())
        detailBinding.txtSutradara.text = R.string.none.toString()

        Glide.with(this)
            .load(BASE_URL_IMAGE + movies?.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
            .error(R.drawable.ic_baseline_error_outline)
            .into(detailBinding.poster)

        Glide.with(this)
            .load(BASE_URL_IMAGE + movies?.backdropPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_sync))
            .error(R.drawable.ic_baseline_error_outline)
            .into(detailBinding.bgPoster)
    }

    private fun showMovieGenreDetail(genre: List<GenresItem?>) {
        detailBinding.txtGenre.text = genre[0]?.name
    }

    private fun showMovieCompany(company: List<ProductionCompaniesItem?>) {
        detailBinding.txtProduction.text = company[0]?.name
    }

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