package com.example.imagesearchapp.data.remote.imageSearch

import com.example.imagesearchapp.data.remote.imageSearch.error.SingleExceptionTransformer
import com.example.imagesearchapp.data.remote.imageSearch.request.ImageSearchRequest
import com.example.imagesearchapp.data.repository.imageSearch.ImageSearchRemoteDataSource
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchResponseVO
import io.reactivex.Single

class ImageSearchRemoteDataSourceImpl(private val imageSearchAPI: ImageSearchAPI) : ImageSearchRemoteDataSource {
    override fun searchImage(imageSearchRequest: ImageSearchRequest): Single<ImageSearchResponseVO> {
        return imageSearchRequest.run {
            imageSearchAPI.searchImage(query = query, sort = sort.type, page = page, size = size)
                .compose(SingleExceptionTransformer())
        }
    }
}