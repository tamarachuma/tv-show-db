package com.project.popularmovies.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.popularmovies.data.models.MovieCardModel
import com.project.popularmovies.data.repository.Repository
import com.project.popularmovies.ui.base.BaseViewModel
import com.project.popularmovies.utils.toMovieCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel : BaseViewModel() {
    private val _searchMovieList = MutableLiveData<List<MovieCardModel>>()
    val searchMovieList: LiveData<List<MovieCardModel>> get() = _searchMovieList
    private val _loadingMore = MutableLiveData(false)
    val loadingMore: LiveData<Boolean> get() = _loadingMore
    private var noMoreData = false
    private var page = 1


    fun onScrollEndReached(string: CharSequence) {
        if (loadingMore.value == true || noMoreData) return
        page++
        loadMore(string)
    }

    fun loadMore(string: CharSequence){
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                try {
                    _loadingMore.postValue(true)
                    val movies = Repository.getMovieByName(string = string.toString(), page)
                    _searchMovieList.postValue((_searchMovieList.value ?: emptyList()) + movies.toMovieCardModel())
                    Log.d("getsCall", "loadMore: movie size ${movies.size}, ${string}")
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    _loadingMore.postValue(false)
                }
            }
        }
    }


    fun onSearchTextChange(string: CharSequence?) {
        if (string.isNullOrEmpty()) _searchMovieList.postValue(emptyList())
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                try {
                    showLoading()
                    _loadingMore.postValue(true)
                    val movies = Repository.getMovieByName(string = string.toString(), page)
                    Log.d("aftertextChange", "OnSearchTextChange getcalled: movie size ${movies.size}, ${string}")
                    _searchMovieList.postValue(movies.toMovieCardModel())
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    _loadingMore.postValue(false)
                    hideLoading()
                }
            }
        }
    }


}