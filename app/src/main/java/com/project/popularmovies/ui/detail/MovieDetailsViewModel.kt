package com.project.popularmovies.ui.detail

import androidx.lifecycle.*
import com.project.popularmovies.R
import com.project.popularmovies.data.models.MovieCardModel
import com.project.popularmovies.data.repository.Repository
import com.project.popularmovies.ui.base.BaseViewModel
import com.project.popularmovies.ui.base.DialogData
import com.project.popularmovies.utils.toMovieCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(data: MovieCardModel) : BaseViewModel() {

    private val _similarMovie = MutableLiveData<List<MovieCardModel>>()
    val similarMovie: LiveData<List<MovieCardModel>> get() = _similarMovie

    private val _loadingMore = MutableLiveData(false)
    val loadingMore: LiveData<Boolean> get() = _loadingMore

    val id = data.id

    private var noMoreData = false

    private var page = 1


    init {
        getSimilarListOfMovies()
    }

    fun onScrollEndReached() {
        if (loadingMore.value == true || noMoreData) return
        getSimilarListOfMovies()
    }


    private fun getSimilarListOfMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loadingMore.postValue(true)
                val similarMovies = Repository.getSimilarMovies(id, page)
                noMoreData = similarMovies.results.size == similarMovies.totalResults
                page++
                _similarMovie.postValue(
                    (_similarMovie.value ?: emptyList()) + similarMovies.results.toMovieCardModel()
                )
            } catch (e: Exception) {
                showDialog(DialogData(title = R.string.common_error, message = e.message ?: ""))
            } finally {
                _loadingMore.postValue(false)
            }
        }
    }

    class CardDetailsViewModelFactory(private val data: MovieCardModel) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(data) as T
        }
    }
}