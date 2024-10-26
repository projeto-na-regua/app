package com.example.na_regua_app.data.model

data class EnderecoConsulta(
    val cep: String,
    val logradouro: String,
    val numero: Int,           // Integer foi convertido para Int
    val complemento: String?,  // Definido como nullable para permitir valores nulos
    val cidade: String,
    val estado: String
)

