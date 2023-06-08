package jp.matsuura.pokemon.androidapp.ext

fun String.extractPokemonId(): String {
    return replace(
        "https://pokeapi.co/api/v2/pokemon/",
        "",
    ).replace(
        "/",
        "",
    )
}