package jp.matsuura.pokemon.androidapp.utility

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object Constant {

    const val BASE_URL = "https://pokeapi.co/"

    val MOSHI: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
}
