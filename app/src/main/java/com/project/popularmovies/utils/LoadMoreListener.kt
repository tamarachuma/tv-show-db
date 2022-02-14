package com.project.popularmovies.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMoreListenerGrid(val callback: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val gridLayoutManager = (recyclerView.layoutManager as GridLayoutManager)
        if (recyclerView.adapter?.itemCount == gridLayoutManager.findLastVisibleItemPosition() + 1) {
            callback()
        }
    }
}

class LoadMoreListenerLinear(val callback: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val linearLayoutManager = (recyclerView.layoutManager as LinearLayoutManager)
        if (recyclerView.adapter?.itemCount == linearLayoutManager.findLastVisibleItemPosition() + 1) {
            callback()
        }
    }
}



