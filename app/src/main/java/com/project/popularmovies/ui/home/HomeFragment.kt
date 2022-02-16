package com.project.popularmovies.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.popularmovies.R
import com.project.popularmovies.databinding.HomeFragmentBinding
import com.project.popularmovies.ui.base.BaseFragment
import com.project.popularmovies.ui.detail.MovieDetailsFragmentDirections
import com.project.popularmovies.utils.LoadMoreListenerGrid
import com.project.popularmovies.utils.startAnimation


class HomeFragment : BaseFragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    override fun getViewModelInstance() = viewModel

    private val adapter = HomeRecyclerViewAdapter {
        val action = MovieDetailsFragmentDirections.movieDetailsFragment(it)
        activity?.findNavController(R.id.mainContainer)?.navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.circle_grow_anim).apply {
                duration = 700
                interpolator = AccelerateDecelerateInterpolator()
            }
        binding.apply {
            fab.setOnClickListener {
                fab.isVisible = false
                fabHome.isVisible = false
                circle.isVisible = true
                circle.startAnimation(animation) {
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                    binding.circle.isVisible = false
                }
            }
            fabHome.setOnClickListener {
                viewModel.onRefresh()
            }
            recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
            recycleView.adapter = adapter
            viewModel.movie.observe(viewLifecycleOwner) {
                adapter.movieList = it
            }
            recycleView.addOnScrollListener(LoadMoreListenerGrid() {
                viewModel.onScrollEndReached()
            })
            swipeToRefresh.setOnRefreshListener {
                viewModel.onRefresh()
            }
            viewModel.loadingMore.observe(viewLifecycleOwner) {
                adapter.loadingMore = it
                if (swipeToRefresh.isRefreshing && it) swipeToRefresh.isRefreshing = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}