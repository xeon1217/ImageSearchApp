package com.example.imagesearchapp.data.repository.imageSearch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * KAKAO ImageSearchAPI response 중 meta 를 정의한 data class
 * document - 검색 결과 리스트
 * - collection : 컬렉션, String
 * - thumbnail_url : 미리보기 이미지 URL, String
 * - image_url : 이미지 URL, String
 * - width : 이미지의 가로 길이, Integer
 * - height : 이미지의 세로 길이, Integer
 * - display_sitename : 출처, String
 * - doc_url : 문서 URL, String
 * - datetime : 문서 작성시간, datetime
 */
data class ImageSearchDocumentVO(
    @SerializedName("collection") val collection: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("width") val width: String,
    @SerializedName("height") val height: String,
    @SerializedName("display_sitename") val displaySiteName: String,
    @SerializedName("doc_url") val docUrl: String,
    @SerializedName("datetime") val dateTime: String
) : Serializable