package com.project.popularmovies.data.models
import com.squareup.moshi.Json


data class PaginatedData<T>(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: T,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)