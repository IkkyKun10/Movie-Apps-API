package com.riezki.latihan.moviecatalogdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riezki.latihan.moviecatalogdb.core.domain.model.Movies
import com.riezki.latihan.moviecatalogdb.core.domain.usecase.MovieUseCase

class MovieDetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

//    fun getDetailMovie(movieId: Int) =
//        movieUseCase.getDetailMovie(movieId).asLiveData()
//
//
//    fun getDetailGenre(movieId: Int) =
//        movieUseCase.getGenreMovieDetail(movieId).asLiveData()
//
//
//    fun getCompany(movieId: Int) =
//        movieUseCase.getCompaniesMovieDetail(movieId).asLiveData()


    fun setFavoriteMovie(movie: Movies, state: Boolean) =
        movieUseCase.setFavoriteMovie(movie, state)



    /**
    private val TAG = "MovieDetailViewModel"
    private var movieDetail = MutableLiveData<DetailMovieResponse?>()
    private val genreItem = MutableLiveData<List<GenresItem?>?>()
    private val companiItem = MutableLiveData<List<ProductionCompaniesItem>?>()

    fun setDetailMovie(movieId: Int) {
        val client = ApiConfig.apiInsntance.getDetailMovie(movieId)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful) {
                    movieDetail.postValue(response.body())
                    genreItem.postValue(response.body()?.genres)
                    companiItem.postValue(response.body()?.productionCompanies)
                } else {
                    Log.d(TAG, "Failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}")
            }

        })
    }


    fun getDetailResponse() : LiveData<DetailMovieResponse?> {
        return movieDetail
    }

    fun getGenre() : LiveData<List<GenresItem?>?> {
        return genreItem
    }

    fun getCompanyItem() : LiveData<List<ProductionCompaniesItem>?> {
        return companiItem
    }
     **/
}