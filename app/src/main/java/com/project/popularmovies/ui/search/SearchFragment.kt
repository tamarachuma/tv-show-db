package com.project.popularmovies.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.popularmovies.R
import com.project.popularmovies.databinding.SearchFragmentBinding
import com.project.popularmovies.ui.base.BaseFragment
import com.project.popularmovies.ui.detail.MovieDetailsFragmentDirections
import com.project.popularmovies.utils.LoadMoreListenerLinear
import com.project.popularmovies.utils.showKeyboard

class SearchFragment : BaseFragment() {

    private val viewModel by viewModels<SearchViewModel>()
    override fun getViewModelInstance() = viewModel

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val adapter = SearchAdapter {
        val action = MovieDetailsFragmentDirections.movieDetailsFragment(it)
        activity?.findNavController(R.id.mainContainer)?.navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchInput.showKeyboard()
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter
        val searchInput = binding.searchInput
        searchInput.doAfterTextChanged { text ->
            viewModel.onSearchTextChange(text)
            Log.d(
                "aftertextChange",
                "after text change called from: movie list size in adapter: ${adapter.searchMovieList.size} $text"
            )
        }
        viewModel.searchMovieList.observe(viewLifecycleOwner) {
            adapter.searchMovieList = it
            Log.d(
                "getsCall",
                "movie list observer : movie list size in adapter: ${adapter.searchMovieList.size}"
            )
        }
        binding.searchRecyclerView.addOnScrollListener(LoadMoreListenerLinear() {
            viewModel.onScrollEndReached(binding.searchInput.text.toString())
        })
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}