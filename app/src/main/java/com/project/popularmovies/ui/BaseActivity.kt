package com.project.popularmovies.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.ContentFrameLayout
import com.project.popularmovies.R

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var loadingView: View

    private lateinit var contentView: ContentFrameLayout

    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView = findViewById(android.R.id.content)
        loadingView = layoutInflater.inflate(R.layout.dialog_loading, contentView, false)
    }


    fun showLoading() {
        if (loading) return
        contentView.addView(loadingView)
        loading = true
    }

    fun hideLoading() {
        if (!loading) return
        contentView.removeView(loadingView)
        loading = false
    }

}