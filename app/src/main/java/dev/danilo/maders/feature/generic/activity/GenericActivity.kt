package dev.danilo.maders.feature.generic.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.danilo.maders.R
import dev.danilo.maders.databinding.ActivityGenericBinding
import dev.danilo.maders.extension.setupNavigationToolbar

class GenericActivity : AppCompatActivity() {

    companion object {
        const val TITLE_PARAM =
            "dev.danilo.maders.feature.generic.activity.GenericActivity.TITLE_PARAM"
        const val TEXT_PARAM =
            "dev.danilo.maders.feature.generic.activity.GenericActivity.TEXT_PARAM"
    }

    private lateinit var binding: ActivityGenericBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenericBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.toolbar.title = intent.getStringExtra(TITLE_PARAM)
        binding.tvText.text = intent.getStringExtra(TEXT_PARAM)
        setupNavigationToolbar(binding.toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}

