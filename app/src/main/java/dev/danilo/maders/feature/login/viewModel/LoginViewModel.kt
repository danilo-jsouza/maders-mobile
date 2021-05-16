package dev.danilo.maders.feature.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danilo.maders.SharedPreferences
import dev.danilo.maders.feature.login.repository.UserRepository
import dev.danilo.maders.model.Auth
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import dev.danilo.maders.extension.Result
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _login = MutableLiveData<Result<Auth>>()
    val login: LiveData<Result<Auth>> get() = _login

    fun handleLogin(user: String, password: String) = viewModelScope.launch {
        repository.fetchLogin(user, password)
            .onStart { _login.postValue(Result.Loading) }
            .catch { _login.postValue(Result.Error(it)) }
            .collect {
                sharedPreferences.setToken(it.token)
                _login.postValue(Result.Success(it))
            }
    }
}