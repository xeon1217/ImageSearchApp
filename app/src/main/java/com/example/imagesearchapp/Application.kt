package com.example.imagesearchapp

import android.app.Application
import com.example.imagesearchapp.di.networkModule
import com.example.imagesearchapp.di.repositoryModule
import com.example.imagesearchapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@Application)
            androidFileProperties()
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}