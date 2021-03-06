package com.project.popularmovies.data.network

import com.project.popularmovies.data.models.Movie
import com.project.popularmovies.data.models.PaginatedData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("tv/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): PaginatedData<List<Movie>>

    @GET("search/tv")
    suspend fun getMovieByName(
        @Query("query") query: String,
        @Query("page") page: Int
    ): PaginatedData<List<Movie>>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarMovies(
        @Path("tv_id") tv_id: Int,
        @Query("page") page: Int
    ): PaginatedData<List<Movie>>

}