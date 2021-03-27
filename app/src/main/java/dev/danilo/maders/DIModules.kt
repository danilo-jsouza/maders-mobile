package dev.danilo.maders

import dev.danilo.maders.feature.login.viewModel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object DIModules {
    fun inject(): List<Module> {
        return listOf(viewModelModule)
    }

    val viewModelModule = module {
        viewModel { LoginViewModel() }
    }
}
