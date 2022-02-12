package com.project.popularmovies.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.popularmovies.R
import com.project.popularmovies.data.models.Movie
import com.project.popularmovies.data.models.MovieCardModel
import com.project.popularmovies.data.repository.Repository
import com.project.popularmovies.ui.base.BaseViewModel
import com.project.popularmovies.ui.base.DialogData
import com.project.popularmovies.utils.toPopularListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val _movie = MutableLiveData<List<MovieCardModel>>()
    val movie: LiveData<List<MovieCardModel>> get() = _movie

    private val _loadingMore = MutableLiveData(false)
    val loadingMore: LiveData<Boolean> get() = _loadingMore

    private var noMoreData = false

    private var page = 1

    init {
        loadMore()
    }

    fun onScrollEndReached() {
        if (loadingMore.value == true || noMoreData) return
        loadMore()
    }

    fun onRefresh() {
        page = 1
        _movie.postValue(emptyList())
        loadMore()
    }

    private fun loadMore() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loadingMore.postValue(true)
                val movies = Repository.getAllMovies(
                    page = page
                )
                noMoreData = movies.results.size == PAGE_SIZE
                page++
                _movie.postValue((_movie.value ?: emptyList()) + movies.results.toPopularListModel())
            } catch (e: Exception) {
                showDialog(DialogData(title = R.string.common_error, message = e.message ?: ""))
            } finally {
                _loadingMore.postValue(false)
            }
        }
    }

    companion object{
        val PAGE_SIZE = 20
    }
}