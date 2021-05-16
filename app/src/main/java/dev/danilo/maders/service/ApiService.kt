package dev.danilo.maders.service

import android.content.Context
import dev.danilo.maders.SharedPreferences
import dev.danilo.maders.model.Auth
import dev.danilo.maders.model.Portion
import dev.danilo.maders.model.RegisterRequest
import dev.danilo.maders.model.UserRequest
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object {

        private fun initOkHttpClient(sharedPreferences: SharedPreferences) = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val token = sharedPreferences.getToken().orEmpty()
                val newRequest = request.newBuilder()
                    .addHeader("Authorization", token)
                    .build()

                it.proceed(newRequest)
            }.build()

        fun getService(sharedPreferences: SharedPreferences): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api-petsplace.herokuapp.com/")
                .client(initOkHttpClient(sharedPreferences))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @POST("/login")
    suspend fun login(@Body user: UserRequest): Auth

    @GET("/api/products")
    suspend fun getPortion(): List<Portion>

    @POST("/api/consumers")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<Unit>
}