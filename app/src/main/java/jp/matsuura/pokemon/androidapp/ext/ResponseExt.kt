package jp.matsuura.pokemon.androidapp.ext

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.requireBody(): T {
    val body = body()
    if (body != null && isSuccessful) {
        return body
    } else {
        throw HttpException(this)
    }
}
