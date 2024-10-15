package com.example.na_regua_app.data.model

data class Usuario(
    val nome: String,
    val email: String,
    val senha: String,
    val celular: String,
    val imgPerfil: String,
    val cep: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String,
    val cidade: String,
    val estado: String
)