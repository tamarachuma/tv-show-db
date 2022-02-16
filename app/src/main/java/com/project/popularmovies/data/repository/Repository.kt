package com.project.popularmovies.data.repository

import com.project.popularmovies.data.network.NetworkClient

object Repository {
    suspend fun getAllMovies(page: Int) =
        NetworkClient.movieService.getPopularMovies(page = page)

    suspend fun getMovieByName(string: String, page: Int) =
        NetworkClient.movieService.getMovieByName(string, page).results

    suspend fun getSimilarMovies(id: Int, page: Int) =
        NetworkClient.movieService.getSimilarMovies(id, page)
}
