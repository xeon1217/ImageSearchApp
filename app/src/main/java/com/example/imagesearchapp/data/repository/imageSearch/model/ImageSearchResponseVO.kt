package com.example.imagesearchapp.data.repository.imageSearch.model

import com.google.gson.annotations.SerializedName

/**
 * https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide
 * 카카오 ImageSearch API request 참고 사이트
 */
data class ImageSearchResponseVO(
    val meta: ImageSearchMetaVO,
    val documents: List<ImageSearchDocumentVO>
)