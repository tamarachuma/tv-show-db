package com.project.popularmovies.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieCardModel(
    val id: Int,
    val name: String,
    val image: String,
    val imdb: Double,
    val overview: String,
    val firstAirDate: String
): Parcelable