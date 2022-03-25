package com.riezki.latihan.core.data

import com.riezki.latihan.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<com.riezki.latihan.core.data.Resource<ResultType>> = flow {
        emit(com.riezki.latihan.core.data.Resource.Loading())
        val dbSource = loadFromDb()?.first()
        if (shouldFecth(dbSource)) {
            emit(com.riezki.latihan.core.data.Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCreateCall(apiResponse.data)
                    loadFromDb()?.let { emitAll(it.map { com.riezki.latihan.core.data.Resource.Success(it) }) }
                }
                is ApiResponse.Empty -> {
                    loadFromDb()?.let { emitAll(it.map { com.riezki.latihan.core.data.Resource.Success(it) }) }
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.riezki.latihan.core.data.Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            loadFromDb()?.let { emitAll(it.map { com.riezki.latihan.core.data.Resource.Success(it) }) }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb() :Flow<ResultType>?

    protected abstract fun shouldFecth(data: ResultType?) :Boolean

    protected abstract suspend fun createCall() : Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCreateCall(data: RequestType)

    fun asFlow() : Flow<com.riezki.latihan.core.data.Resource<ResultType>> = result
}