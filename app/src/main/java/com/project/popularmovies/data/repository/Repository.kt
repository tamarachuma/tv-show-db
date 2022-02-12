package com.project.popularmovies.data.repository

import com.project.popularmovies.data.models.Movie
import com.project.popularmovies.data.models.PaginatedData
import com.project.popularmovies.data.models.Result
import com.project.popularmovies.data.network.NetworkClient

object Repository {
    suspend fun getAllMovies(page: Int) = NetworkClient.movieService.getPopularMovies(page = page)

    suspend fun getMovieById(id: Int) = NetworkClient.movieService.getMovieById(id).result

    suspend fun getMovieByName(string: String, page: Int): Movie {
        return NetworkClient.movieService.getMovieByName(string, page).result
    }
}
