package com.project.popularmovies.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.popularmovies.databinding.MovieDetailsFragmentBinding


class MovieDetailsFragment : Fragment() {

    private lateinit var viewModel: MovieDetailsViewModel
    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.horizontalRecycleView.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}