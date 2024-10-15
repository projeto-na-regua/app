package com.example.na_regua_app.data.model

data class Funcionario(
    val id: Int,
    val nome: String,
    val email: String,
    val imgPerfil: Int? = null,
    val dtype: String
)

