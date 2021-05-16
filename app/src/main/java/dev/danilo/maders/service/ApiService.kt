package dev.danilo.maders.service

import dev.danilo.maders.model.Auth
import dev.danilo.maders.model.Portion
import dev.danilo.maders.model.RegisterRequest
import dev.danilo.maders.model.UserRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object {
        fun getService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api-petsplace.herokuapp.com/github.com/matheussilva123/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @POST("/login")
    fun login(@Body user: UserRequest): Auth

    @GET("api/products")
    fun getPortion(): List<Portion>

    @POST("api/consumers")
    fun register(registerRequest: RegisterRequest): Response<Unit>
}