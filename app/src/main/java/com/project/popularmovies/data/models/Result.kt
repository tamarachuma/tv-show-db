package com.project.popularmovies.data.models
import com.squareup.moshi.Json



data class Result<T>(
    @Json(name = "result")
    val result: T
)