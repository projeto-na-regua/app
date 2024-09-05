package com.example.na_regua_app.classes

data class Postagem(
    val fotoDePerfil: Int,
    val nomeDeUsuario: String,
    val descricao: String,
    val imagem: Int? = null
)
