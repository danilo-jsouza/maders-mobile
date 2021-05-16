package dev.danilo.maders

import android.content.Context

class SharedPreferences(context: Context) {

    private val sharedPref = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)

    fun getToken(): String? = sharedPref.getString(TOKEN_KEY, null)

    fun setToken(token: String?) {
        sharedPref.edit().putString(TOKEN_KEY, token).apply()
    }

    companion object {
        private const val SHARED_KEY = "maders"
        private const val TOKEN_KEY = "maders_token"
    }
}