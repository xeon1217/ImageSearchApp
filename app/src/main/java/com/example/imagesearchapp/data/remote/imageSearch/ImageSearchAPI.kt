package com.example.imagesearchapp.data.remote.imageSearch

import com.example.imagesearchapp.data.repository.imageSearch.model.ImageSearchResponseVO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
 * 카카오 ImageSearch API request 참고 사이트
 */
interface ImageSearchAPI {

    @GET("/v2/search/image")
    fun searchImage (
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Single<ImageSearchResponseVO>
}