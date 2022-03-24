package com.riezki.latihan.moviecatalogdb

import android.app.Application
import com.riezki.latihan.moviecatalogdb.core.di.databaseModule
import com.riezki.latihan.moviecatalogdb.core.di.networkModule
import com.riezki.latihan.moviecatalogdb.core.di.repositoryModule
import com.riezki.latihan.moviecatalogdb.di.useCaseModule
import com.riezki.latihan.moviecatalogdb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}