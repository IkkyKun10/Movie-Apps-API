package com.riezki.latihan.moviecatalogdb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riezki.latihan.moviecatalogdb.api.ApiConfig
import com.riezki.latihan.moviecatalogdb.model.TvResponse
import com.riezki.latihan.moviecatalogdb.model.TvShowItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {
    private val TAG = "TvShowViewModel"
    private var listTvShow = MutableLiveData<ArrayList<TvShowItems>>()

    fun setListTvShow() {
        val client = ApiConfig.apiInsntance.getListTvShow()
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful) {
                    listTvShow.postValue(response.body()?.tvShow)
                } else {
                    Log.d(TAG, "Failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}")
            }

        })
    }

    fun getListTvShow() : LiveData<ArrayList<TvShowItems>> {
        return listTvShow
    }
}