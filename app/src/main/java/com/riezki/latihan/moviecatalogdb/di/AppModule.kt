package com.riezki.latihan.moviecatalogdb.di

import com.riezki.latihan.core.domain.usecase.MovieUseCase
import com.riezki.latihan.core.domain.usecase.MoviesInteractor
import com.riezki.latihan.moviecatalogdb.ui.favorite.FavoriteViewModel
import com.riezki.latihan.moviecatalogdb.ui.detail.MovieDetailViewModel
import com.riezki.latihan.moviecatalogdb.ui.main.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}
