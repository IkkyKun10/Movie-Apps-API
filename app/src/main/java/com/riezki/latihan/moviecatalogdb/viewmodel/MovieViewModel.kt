package com.riezki.latihan.moviecatalogdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riezki.latihan.moviecatalogdb.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()


    /**
    fun setListMovie() {
        val client = ApiConfig.apiInsntance.getListMovie()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    listMovie.postValue(response.body()?.movies)
                } else {
                    Log.d(TAG, "Failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}")
            }

        })
    }

    fun getListMovies() : LiveData<ArrayList<MovieItems>> {
        return listMovie
    }
    **/
}