package com.example.na_regua_app.data.model

import java.time.Instant

data class ChatPost (
    val conteudo: String,
    val tipo: String,
    val imgPerfil: String,
    val midia: String,
    val status: String
)

data class ChatOpen (
    val conteudoGeral: List<ChatPost>
)

data class ChatList (
    val nomeChat: String,
    val ultimaMensage: String,
    val imagemPerfil: String,
    val dataHoraUltimaMensagem: Instant
)

data class ChatUserSide (
    val conteudoUserSide: List<ChatList>
)

data class ChatBarbeariaSide (
    val conteudoBarbeariaSide: List<ChatList>
)
