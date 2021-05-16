package dev.danilo.maders.feature.login.repository

import dev.danilo.maders.model.Auth
import dev.danilo.maders.model.RegisterRequest
import dev.danilo.maders.model.UserRequest
import dev.danilo.maders.service.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class UserRepository(
    private val service: ApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun fetchLogin(user: String, password: String): Flow<Auth> = flow {
        val userRequest = UserRequest(user, password)
        val auth = service.login(userRequest)
        emit(auth)
    }.flowOn(dispatcher)

    fun fetchRegister(user: String, email: String, password: String): Flow<Response<Unit>> = flow {
        val registerRequest = RegisterRequest(user, email, password)
        val register = service.register(registerRequest)
        emit(register)
    }
}
