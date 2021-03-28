package com.example.imagesearchapp.di

import com.example.imagesearchapp.ui.imageSearchDetail.ImageSearchDetailViewModel
import com.example.imagesearchapp.ui.imageSearch.ImageSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ImageSearchViewModel(get(), get())
    }

    viewModel {
        ImageSearchDetailViewModel(get())
    }
}