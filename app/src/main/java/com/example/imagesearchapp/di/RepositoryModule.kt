package com.example.imagesearchapp.di

import com.example.imagesearchapp.data.repository.imageSearch.ImageSearchRepository
import com.example.imagesearchapp.data.repository.imageSearch.ImageSearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ImageSearchRepository> {
        ImageSearchRepositoryImpl(get())
    }
}