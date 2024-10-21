package com.example.na_regua_app.data.model

data class DadosCadastro(
    val nome: String,
    val email: String,
    val senha: String,
    val celular: String,
//    var imgPerfil: String,
    val cep: String,
    val logradouro: String,
    val numero: String,
//    val complemento: String,
    val cidade: String,
    val estado: String
)