package com.example.imagesearchapp.data.remote.imageSearch.request

data class ImageSearchRequest(
    val query: String,
    val sort: ImageSearchRequestSort,
    val page: Int,
    val size: Int,
    val isFirst: Boolean
)

enum class ImageSearchRequestSort(val type: String) {
    ACCURACY("accuracy"),
    RECENCY("recency");
}