package com.riezki.latihan.moviecatalogdb.di

import com.riezki.latihan.moviecatalogdb.core.domain.usecase.MovieUseCase
import com.riezki.latihan.moviecatalogdb.core.domain.usecase.MoviesInteractor
import com.riezki.latihan.moviecatalogdb.ui.favorite.FavoriteViewModel
import com.riezki.latihan.moviecatalogdb.viewmodel.MovieDetailViewModel
import com.riezki.latihan.moviecatalogdb.viewmodel.MovieViewModel
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
