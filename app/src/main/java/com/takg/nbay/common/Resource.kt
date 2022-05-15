package com.takg.nbay.common

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null,
    val e: Exception? = null
) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, exception: Exception? = null) :
        Resource<T>(data, message, exception)


}
