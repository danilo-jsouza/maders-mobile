package dev.danilo.maders.feature.products.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import dev.danilo.maders.R
import dev.danilo.maders.SharedPreferences
import dev.danilo.maders.SplashActivity
import dev.danilo.maders.base.BaseFragment
import dev.danilo.maders.databinding.FragmentProductBinding
import dev.danilo.maders.extension.Result
import dev.danilo.maders.feature.generic.activity.GenericActivity
import dev.danilo.maders.feature.products.adapter.OnPortionClicked
import dev.danilo.maders.feature.products.adapter.PortionAdapter
import dev.danilo.maders.feature.products.viewmodel.ProductViewModel
import dev.danilo.maders.feature.settings.activity.SettingsActivity
import dev.danilo.maders.model.Portion
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<FragmentProductBinding>(), OnPortionClicked,
    NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private const val COLUMN_COUNT = 2
    }

    private lateinit var rootLateralMenu: DrawerLayout
    private lateinit var lateralMenu: NavigationView

    private val viewModel: ProductViewModel by viewModel()

    private val sharedPreferences: SharedPreferences by inject()

    override fun getViewBinding() =
        FragmentProductBinding.inflate(LayoutInflater.from(requireContext()))

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_product -> {
                val searchView: SearchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Toast.makeText(context, "texto pesquisado: $query", Toast.LENGTH_SHORT)
                            .show()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }
            R.id.exit_product -> logout()
            R.id.settings_product -> openSettings()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.run {
            rootLateralMenu = findViewById(R.id.root_lateral_menu)
            lateralMenu = findViewById(R.id.menu_lateral)
        }

        binding.floatButton.setOnClickListener {
            openGenericScreen(
                getString(R.string.add),
                getString(R.string.add)
            )
        }
        settingLateralMenu()
        viewModel.fetchPortion()
        viewModel.product.observe(this, Observer { state ->
            val isLoading = state is Result.Loading
            binding.progressBar.isVisible = isLoading
            if(isLoading) return@Observer

            when (state) {
                is Result.Error -> handleError(state.throwable)
                else -> if (state is Result.Success) updateUI(state.data)
            }
        })
    }

    private fun updateUI(data: List<Portion>) {
        binding.rvPortion.run {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            adapter = PortionAdapter(
                data, this@ProductFragment
            )
        }
    }

    private fun handleError(throwable: Throwable) {

    }

    private fun openSettings() {
        val intent = Intent(context, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun settingLateralMenu() {
        val toogle = ActionBarDrawerToggle(
            requireActivity(),
            rootLateralMenu,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        rootLateralMenu.addDrawerListener(toogle)
        toogle.syncState()

        lateralMenu.setNavigationItemSelectedListener(this)
    }

    private fun logout() {
        val intent = Intent(requireContext(), SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        sharedPreferences.setToken(null)
        startActivity(intent)
        activity?.finish()
    }

    override fun getTitle(): Int = R.string.products

    override fun getMenu(): Int = R.menu.products

    override fun onPortionClicked(portion: Portion) {
        openGenericScreen(getString(R.string.portion), portion.name ?: getString(R.string.portion))
    }

    private fun openGenericScreen(title: String, text: String) {
        val intent = Intent(context, GenericActivity::class.java).apply {
            putExtra(GenericActivity.TITLE_PARAM, title)
            putExtra(GenericActivity.TEXT_PARAM, text)
        }
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_add_product -> {
                openGenericScreen(getString(R.string.add_product), getString(R.string.add_product))
            }
            R.id.nav_settings -> openSettings()
            R.id.nav_exit -> logout()
        }

        rootLateralMenu?.closeDrawer(GravityCompat.START)
        return true
    }
}
