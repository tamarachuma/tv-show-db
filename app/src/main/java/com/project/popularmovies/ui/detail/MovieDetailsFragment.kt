package com.project.popularmovies.ui.detail
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.project.popularmovies.R
import com.project.popularmovies.databinding.MovieDetailsFragmentBinding
import com.project.popularmovies.utils.LoadMoreListenerLinear
import com.project.popularmovies.utils.setImage
import java.math.RoundingMode


class MovieDetailsFragment : Fragment() {

    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val movieDetailArgs by navArgs<MovieDetailsFragmentArgs>()
    private val viewModel by viewModels<MovieDetailsViewModel> {
        MovieDetailsViewModel.CardDetailsViewModelFactory(movieDetailArgs.result)
    }

    private val adapter = SimilarMovieAdapter {
        val action = MovieDetailsFragmentDirections.movieDetailsFragment(it)
        activity?.findNavController(R.id.mainContainer)?.navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayMovieData()
        val recyclerView = binding.horizontalRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.horizontalRecyclerview)
        viewModel.similarMovie.observe(viewLifecycleOwner) {
            adapter.similarMovieList = it
        }
        recyclerView.addOnScrollListener(LoadMoreListenerLinear() {
            viewModel.onScrollEndReached()
        })
        viewModel.loadingMore.observe(viewLifecycleOwner) {
            adapter.loadingMore = it
        }
    }



    @SuppressLint("SetTextI18n")
    private fun displayMovieData() {
        val data = movieDetailArgs.result
        val rounded = data.imdb.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
        binding.apply {
            movieTitle.text = data.name
            movieDescription.text = data.overview
            movieImageDetail.setImage(data.image)
            date.text = "Date: ${data.firstAirDate}"
            imdb.text = "IMDb: ${rounded}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}