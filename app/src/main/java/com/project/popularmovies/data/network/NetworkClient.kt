package com.project.popularmovies.data.network

import com.project.popularmovies.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkClient {

    val movieService by lazy { createMovieService() }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    private fun createMovieService(): MovieService {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(" https://api.themoviedb.org")
        retrofitBuilder.client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor(TokenInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()
        )
        retrofitBuilder.addConverterFactory(moshiConverter())
        return retrofitBuilder.build().create(MovieService::class.java)
    }

    private fun moshiConverter() =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        )
}