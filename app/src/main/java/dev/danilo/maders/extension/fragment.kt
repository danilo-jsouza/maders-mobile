package dev.danilo.maders.extension

import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import dev.danilo.maders.R

fun Fragment.setupNavigationToolbar(toolbar: Toolbar, @DrawableRes icon: Int = R.drawable.ic_arrow_back) {
    (activity as? AppCompatActivity)?.run {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(icon)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

fun AppCompatActivity.setupNavigationToolbar(toolbar: Toolbar, @DrawableRes icon: Int = R.drawable.ic_arrow_back) {
    setSupportActionBar(toolbar)
    toolbar.setNavigationIcon(icon)
    actionBar?.setHomeButtonEnabled(true)
    actionBar?.setDisplayHomeAsUpEnabled(true)
}