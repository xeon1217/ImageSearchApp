package com.example.imagesearchapp.data.repository.imageSearch

import com.example.imagesearchapp.data.remote.imageSearch.error.SingleExceptionTransformer
import com.example.imagesearchapp.data.remote.imageSearch.request.ImageSearchRequest
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchResponseVO
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ImageSearchRepositoryImpl(
    private val imageSearchRemoteDataSource: ImageSearchRemoteDataSource
) : ImageSearchRepository {
    override fun getImage(imageSearchRequest: ImageSearchRequest): Single<ImageSearchResponseVO> {
        return imageSearchRemoteDataSource.searchImage(imageSearchRequest)
            .subscribeOn(Schedulers.io())
    }
}