package com.example.imagesearchapp.di

import com.example.imagesearchapp.BuildConfig
import com.example.imagesearchapp.data.remote.imageSearch.ImageSearchAPI
import com.example.imagesearchapp.data.remote.imageSearch.ImageSearchRemoteDataSourceImpl
import com.example.imagesearchapp.data.repository.imageSearch.ImageSearchRemoteDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<ImageSearchRemoteDataSource> {
        ImageSearchRemoteDataSourceImpl(get())
    }

    single {
        get<Retrofit>().create(ImageSearchAPI::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get())
            addInterceptor(HttpLoggingInterceptor().apply {
                if(BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()
    }

    single {
        Interceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .header("Authorization", BuildConfig.KAKAO_API_KEY)
                    .build()
            )
        }
    }
}