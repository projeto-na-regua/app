package com.example.na_regua_app.data.model

data class Postagem(
    val fotoDePerfil: String,
    val nomeDeUsuario: String,
    val descricao: String,
    val imagem: Int? = null
)
