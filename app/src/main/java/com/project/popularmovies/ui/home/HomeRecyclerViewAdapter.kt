package com.project.popularmovies.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.popularmovies.data.models.Movie
import com.project.popularmovies.data.models.MovieCardModel
import com.project.popularmovies.databinding.MovieItemBinding
import com.project.popularmovies.utils.setImage

class HomeRecyclerViewAdapter(private val onItemClick: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.MovieViewHolder>() {

    var movieList: List<MovieCardModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var loadingMore = false
        set(value) {
            field = value
            notifyItemChanged(itemCount - 1)
        }

    class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movieName.text = movieList[position].name
        holder.binding.movieRateText.text = "IMDb: "
        holder.binding.movieRate.text = movieList[position].imdb.toString()
        holder.binding.movieImage.setImage(movieList[position].image)
    }

    override fun getItemCount() = movieList.size


    private val onClickListener = View.OnClickListener { v ->
        val card = v?.tag as Movie
        onItemClick.invoke(card)
    }
}