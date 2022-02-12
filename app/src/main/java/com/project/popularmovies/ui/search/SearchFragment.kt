package com.project.popularmovies.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.popularmovies.R
import com.project.popularmovies.databinding.MovieDetailsFragmentBinding
import com.project.popularmovies.databinding.SearchFragmentBinding
import com.project.popularmovies.ui.detail.MovieDetailsViewModel
import com.project.popularmovies.utils.showKeyboard

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!


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