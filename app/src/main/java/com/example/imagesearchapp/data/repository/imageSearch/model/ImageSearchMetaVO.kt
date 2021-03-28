package com.example.imagesearchapp.data.repository.imageSearch.model

import com.google.gson.annotations.SerializedName

/**
 * KAKAO ImageSearchAPI response 중 meta 를 정의한 data class
 * meta : 검색 메타데이터
 * - total_count : 검색된 문서 수, Integer
 * - pageable_count : total_count 중에 노출가능한 수, Integer
 * - is_end : 현재 페이지가 마지막 페이지인지 여부, 값이 false이면 page를 증가시켜 다음 페이지를 요청할 수 있음, Boolean
 */
data class ImageSearchMetaVO(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)
