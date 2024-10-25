package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.ChatBarbeariaSide
import com.example.na_regua_app.data.model.ChatOpen
import com.example.na_regua_app.data.model.ChatPost
import com.example.na_regua_app.data.model.ChatUserSide
import retrofit2.Response

interface ChatRepository {
    suspend fun postarChat(id: Int, mensagem: String, tipo: String, imagem: String): Response<ChatPost>

    suspend fun abrirChat(id: Int, tipo: String) : Response<ChatOpen>

    suspend fun chatUserSide() : Response<ChatUserSide>

    suspend fun chatBarbeariaSide() : Response<ChatBarbeariaSide>
}