package com.project.popularmovies.ui.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.project.popularmovies.R
import com.project.popularmovies.ui.BaseActivity
import com.project.popularmovies.ui.MainActivity

fun Fragment.showDialog(@StringRes title: Int, @StringRes message: Int) {
    showDialog(title, getString(message))
}

fun Fragment.showDialog(@StringRes title: Int, message: String) {
    context?.let {
        AlertDialog.Builder(it)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(
                R.string.common_Ok
            ) { dialog, _ -> dialog.dismiss() }
            .setCancelable(true)
            .show()

    }
}

fun Fragment.showLoading() {
    (activity as? BaseActivity)?.showLoading()
}

fun Fragment.hideLoading() {
    (activity as? BaseActivity)?.hideLoading()
}
