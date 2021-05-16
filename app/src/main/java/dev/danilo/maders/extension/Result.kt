package dev.danilo.maders.extension

sealed class Result<out T: Any> {
    object Loading: Result<Nothing>()
    data class Success<out T: Any>(val data: T): Result<T>()
    data class Error(val throwable: Throwable): Result<Nothing>()
}