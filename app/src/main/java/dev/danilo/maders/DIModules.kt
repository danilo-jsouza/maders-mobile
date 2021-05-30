package dev.danilo.maders

import dev.danilo.maders.feature.login.repository.UserRepository
import dev.danilo.maders.feature.login.viewModel.LoginViewModel
import dev.danilo.maders.feature.products.repository.ProductRepository
import dev.danilo.maders.feature.products.viewmodel.ProductViewModel
import dev.danilo.maders.feature.register.viewmodel.RegisterViewModel
import dev.danilo.maders.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
object DIModules {
    fun inject(): List<Module> {
        return listOf(viewModelModule, networkModule, repositoryModule, databaseModule)
    }

    private val networkModule = module {
        single { SharedPreferences(get()) }
        single { ApiService.getService(get()) } bind ApiService::class
    }

    private val databaseModule = module {
        single { DatabaseManager(get()) }
    }

    private val repositoryModule = module {
        factory { UserRepository(get()) }
        factory { ProductRepository(get(), get()) }
    }

    private val viewModelModule = module {
        viewModel { LoginViewModel(get(), get()) }
        viewModel { RegisterViewModel(get(), get()) }
        viewModel { ProductViewModel(get(), get()) }
    }
}
