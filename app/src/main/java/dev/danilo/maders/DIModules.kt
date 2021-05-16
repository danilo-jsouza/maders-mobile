package dev.danilo.maders

import dev.danilo.maders.feature.login.repository.UserRepository
import dev.danilo.maders.feature.login.viewModel.LoginViewModel
import dev.danilo.maders.service.ApiService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

object DIModules {
    fun inject(): List<Module> {
        return listOf(viewModelModule, networkModule, repositoryModule)
    }

    private val networkModule = module {
        single { ApiService.getService() } bind ApiService::class
    }

    private val repositoryModule = module {
        factory { UserRepository(get()) }
    }

    private val viewModelModule = module {
        viewModel { LoginViewModel(get()) }
    }
}
