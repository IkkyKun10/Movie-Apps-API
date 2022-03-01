package com.riezki.latihan.moviecatalogdb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riezki.latihan.moviecatalogdb.api.ApiConfig
import com.riezki.latihan.moviecatalogdb.model.DetailMovieResponse
import com.riezki.latihan.moviecatalogdb.model.GenresItem
import com.riezki.latihan.moviecatalogdb.model.ProductionCompaniesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

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

//    fun setGenreMovie(movieId: Int) {
//        val client = ApiConfig.apiInsntance.getDetailMovie(movieId)
//        client.enqueue(object : Callback<GenresItem> {
//            override fun onResponse(call: Call<GenresItem>, response: Response<GenresItem>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<GenresItem>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

    // buatkan fungsi getter 2 atau lebih

    fun getDetailResponse() : LiveData<DetailMovieResponse?> {
        return movieDetail
    }

    fun getGenre() : MutableLiveData<List<GenresItem?>?> {
        return genreItem
    }

    fun getCompanyItem() : LiveData<List<ProductionCompaniesItem>?> {
        return companiItem
    }
}