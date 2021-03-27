package dev.danilo.maders.feature.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    companion object {
        private const val MOCK_USER = "aluno"
        private const val MOCK_PASS = "impacta"
    }
    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> get() = _login

    fun handleLogin(user: String, pass: String) {
        _login.postValue(user == MOCK_USER && pass == MOCK_PASS)
    }
}