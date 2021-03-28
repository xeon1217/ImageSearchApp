package com.example.imagesearchapp.data.remote.imageSearch.error

import com.example.imagesearchapp.data.remote.imageSearch.request.KakaoImageSearchAPIExceptionConverter
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class SingleExceptionTransformer <T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext { throwable ->
            Single.error(KakaoImageSearchAPIExceptionConverter.toRepositoryException(throwable))
        }
    }
}