package dev.danilo.maders.feature.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.danilo.maders.SharedPreferences
import dev.danilo.maders.extension.Result
import dev.danilo.maders.feature.login.repository.UserRepository
import dev.danilo.maders.model.Auth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class RegisterViewModel(
    private val repository: UserRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    private val _register = MutableLiveData<Result<Auth>>()
    val register: LiveData<Result<Auth>> get() = _register

    fun fetchRegister(user: String, password: String, email: String) = viewModelScope.launch {
        repository.fetchRegister(user, email, password)
            .onStart { _register.postValue(Result.Loading) }
            .flatMapConcat { repository.fetchLogin(email, password) }
            .catch { _register.postValue(Result.Error(it)) }
            .collect {
                sharedPreferences.setToken(it.token)
                _register.postValue(Result.Success(it))
            }
    }
}
