package com.project.popularmovies.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.popularmovies.R
import com.project.popularmovies.data.models.MovieCardModel
import com.project.popularmovies.databinding.MovieItemBinding
import com.project.popularmovies.databinding.SimilarMovieItemBinding
import com.project.popularmovies.utils.setImage

class SimilarMovieAdapter(private val onItemClick: (movie: MovieCardModel) -> Unit) :
    RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    var similarMovieList: List<MovieCardModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var loadingMore = false
        set(value) {
            field = value
            notifyItemChanged(itemCount - 1)
        }

    class SimilarMovieViewHolder(
        val binding: SimilarMovieItemBinding,
        onClickListener: View.OnClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val binding =
            SimilarMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return SimilarMovieViewHolder(binding, onClickListener)
    }

    private val onClickListener = View.OnClickListener { v ->
        val card = v?.tag as MovieCardModel
        onItemClick.invoke(card)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        val item = similarMovieList[position]
        holder.binding.similarMovieImage.setImage(item.image)
        holder.binding.similarImdb.text = item.imdb.toString()
        holder.binding.similarTitle.text = item.name
        holder.binding.root.tag = item
    }

    override fun getItemCount() = similarMovieList.size
}