package dev.danilo.maders.feature.settings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import dev.danilo.maders.R
import dev.danilo.maders.base.BaseFragment
import dev.danilo.maders.databinding.FragmentSettingsBinding
import dev.danilo.maders.extension.setupNavigationToolbar

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun getViewBinding() = FragmentSettingsBinding.inflate(LayoutInflater.from(context))

    override fun getTitle(): Int = R.string.settings

    override fun getMenu(): Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar?.let {
            setupNavigationToolbar(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}