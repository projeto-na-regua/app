package com.example.na_regua_app.data.model

data class Barbearia(
    val id: Int,
    val imgPerfil: String?,
    val imgBanner: String?,
    val nomeNegocio: String,
    val emailNegocio: String,
    val celularNegocio: String,
    val descricao: String?,
    val cep: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String?,
    val cidade: String,
    val estado: String,
    val diaSemanas: List<DiaSemana>
)

data class BarbeariaDTO(
    val nomeNegocio: String,
    val emailNegocio: String,
    val celularNegocio: String,
    val descricao: String?,
    val cep: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String?,
    val cidade: String,
    val estado: String,
    val diaSemanas: List<DiaSemana>
)