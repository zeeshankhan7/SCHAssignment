package com.zak.datauses.views

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.zak.datauses.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {
    @BindView(R.id.parentLayout)
    var parentLayout: View? = null
    protected open fun initViews() {
        ButterKnife.bind(this)
    }

    protected fun showErrorActionSnackBar(message: String?) {
        val snackbar = Snackbar.make(parentLayout!!, message!!, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Dismiss") { snackbar.dismiss() }.setActionTextColor(resources.getColor(android.R.color.holo_red_light)).show()
    }
}