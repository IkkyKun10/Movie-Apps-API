/**
package com.riezki.latihan.moviecatalogdb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riezki.latihan.moviecatalogdb.api.ApiConfig
import com.riezki.latihan.moviecatalogdb.model.CreatedByItem
import com.riezki.latihan.moviecatalogdb.model.DetailTvResponse
import com.riezki.latihan.moviecatalogdb.model.GenresItemTv
import com.riezki.latihan.moviecatalogdb.model.ProductionCompaniesItemTv
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvDetailViewModel : ViewModel() {

    private val TAG = "TvDetailViewModel"
    private val detailTvShow = MutableLiveData<DetailTvResponse?>()
    private val genreTv = MutableLiveData<List<GenresItemTv?>?>()
    private val productionCompanies = MutableLiveData<List<ProductionCompaniesItemTv?>?>()
    private val createdBy = MutableLiveData<List<CreatedByItem?>?>()

    fun setDetailTvShow(tvShowId: Int) {
        val client = ApiConfig.apiInsntance.getDetailTvShow(tvShowId)
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(call: Call<DetailTvResponse>, response: Response<DetailTvResponse>) {
                if (response.isSuccessful) {
                    detailTvShow.postValue(response.body())
                    genreTv.postValue(response.body()?.genres)
                    productionCompanies.postValue(response.body()?.productionCompanies)
                    createdBy.postValue(response.body()?.createdBy)
                } else {
                    Log.d(TAG, "fail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.e(TAG, "Failure: ${t.message}")
            }

        })
    }

    //tambahkan 2 atau lebih fungsi getter

    fun getDetailTv() : LiveData<DetailTvResponse?> {
        return detailTvShow
    }

    fun getGenreTvShow() : LiveData<List<GenresItemTv?>?> {
        return genreTv
    }

    fun getCompanies() : LiveData<List<ProductionCompaniesItemTv?>?> {
        return productionCompanies
    }

    fun getCreatedBy() : LiveData<List<CreatedByItem?>?> {
        return createdBy
    }
}
 **/