package com.riezki.latihan.moviecatalogdb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riezki.latihan.moviecatalogdb.api.ApiConfig
import com.riezki.latihan.moviecatalogdb.model.MovieItems
import com.riezki.latihan.moviecatalogdb.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val TAG = "MovieViewModel"
    private var listMovie = MutableLiveData<ArrayList<MovieItems>>()

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
}