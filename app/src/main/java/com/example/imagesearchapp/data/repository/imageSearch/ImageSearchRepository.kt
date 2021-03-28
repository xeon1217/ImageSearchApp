package com.example.imagesearchapp.data.repository.imageSearch

import com.example.imagesearchapp.data.remote.imageSearch.request.ImageSearchRequest
import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchResponseVO
import io.reactivex.Single

interface ImageSearchRepository {
    fun getImage(imageSearchRequest: ImageSearchRequest) : Single<ImageSearchResponseVO>
}