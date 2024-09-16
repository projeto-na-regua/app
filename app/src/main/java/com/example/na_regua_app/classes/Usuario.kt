package com.example.na_regua_app.classes

data class Usuario(
    val email: String,
    val senha: String,
    val dtype: String,
)

fun usuarios(): List<Usuario> {
    return listOf<Usuario>(
        Usuario(email ="melissa@gmail.com", senha = "senha", dtype = "Barbeiro"),
        Usuario(email ="vitor@gmail.com", senha = "senha", dtype = "Cliente")
    )
}