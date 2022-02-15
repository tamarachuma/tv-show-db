package com.project.popularmovies.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.popularmovies.data.models.MovieCardModel
import com.project.popularmovies.databinding.SearchItemBinding
import com.project.popularmovies.utils.setImage

class SearchAdapter(private val onItemClick: (movie: MovieCardModel) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var searchMovieList: List<MovieCardModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class SearchViewHolder(val binding: SearchItemBinding, onClickListener: View.OnClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener(onClickListener)
        }
    }

    private val onClickListener = View.OnClickListener { v ->
        val card = v?.tag as MovieCardModel
        onItemClick.invoke(card)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val binding =
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return SearchViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = searchMovieList[position]
        holder.binding.searchMovieTitle.text = item.name
        holder.binding.searchImdb.text = item.imdb.toString()
        holder.binding.searchMovieImage.setImage(item.image)
        holder.binding.searchDate.text = item.firstAirDate
        holder.binding.root.tag = item
    }

    override fun getItemCount() = searchMovieList.size
}