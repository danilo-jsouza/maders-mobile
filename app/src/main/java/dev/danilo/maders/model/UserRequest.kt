package dev.danilo.maders.model
import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("password")
    var password: String,
    @SerializedName("email")
    var email: String
)
