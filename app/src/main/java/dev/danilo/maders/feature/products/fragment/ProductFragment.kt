package dev.danilo.maders.feature.products.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import dev.danilo.maders.R
import dev.danilo.maders.SplashActivity
import dev.danilo.maders.base.BaseFragment
import dev.danilo.maders.databinding.FragmentProductBinding
import dev.danilo.maders.feature.login.viewModel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<FragmentProductBinding>() {
    private val viewModel: LoginViewModel by viewModel()

    override fun getViewBinding() = FragmentProductBinding.inflate(LayoutInflater.from(requireContext()))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search_product -> TODO()
            R.id.exit_product -> logout()
            R.id.settings_product -> TODO()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        val intent = Intent(requireContext(), SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        activity?.finish()
    }

    override fun getTitle(): Int = R.string.products

    override fun getMenu(): Int = R.menu.products
}
