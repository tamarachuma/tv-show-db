package com.project.popularmovies.ui

import android.os.Bundle
import com.project.popularmovies.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}