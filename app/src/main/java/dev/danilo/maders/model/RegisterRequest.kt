package dev.danilo.maders.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name")
    val user: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
