package com.project.popularmovies.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.project.popularmovies.R
import com.project.popularmovies.data.models.Movie
import com.project.popularmovies.data.models.MovieCardModel

fun ImageView.setImage(image: String?) {
    Glide.with(context)
        .load(image)
        .placeholder(R.drawable.ic_baseline_image_not_supported_24)
        .centerCrop()
        .into(this)
}

fun List<Movie>.toMovieCardModel(): List<MovieCardModel> {
    return map {
        MovieCardModel(
            id = it.id,
            name = it.name,
            image = if (it.posterPath != null) "${"https://image.tmdb.org/t/p/w500"}${it.posterPath}" else "",
            imdb = it.voteAverage,
            overview = it.overview,
            firstAirDate = it.firstAirDate
        )
    }
}

fun View.startAnimation(animation: Animation, onEnd: () -> Unit) {
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) = Unit
        override fun onAnimationEnd(animation: Animation?) {
            onEnd()
        }

        override fun onAnimationRepeat(animation: Animation?) = Unit
    })
    this.startAnimation(animation)
}


fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

