package com.project.popularmovies.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.popularmovies.data.models.Movie
import com.project.popularmovies.databinding.SimilarMovieItemBinding

class SimilarMovieAdapter() :
    RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    class SimilarMovieViewHolder(val binding: SimilarMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val binding =
            SimilarMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return SimilarMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
//        holder.binding.movieName.text = movies[position].name
//        holder.binding.movieRateText.text = "IMDb: "
//        holder.binding.movieRate.text = movies[position].voteAverage.toString()
    }

    override fun getItemCount() = 6
}