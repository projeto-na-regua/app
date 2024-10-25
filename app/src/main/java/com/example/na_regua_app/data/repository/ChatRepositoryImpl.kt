package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.ChatService
import com.example.na_regua_app.data.model.ChatBarbeariaSide
import com.example.na_regua_app.data.model.ChatOpen
import com.example.na_regua_app.data.model.ChatPost
import com.example.na_regua_app.data.model.ChatUserSide
import retrofit2.Response

class ChatRepositoryImpl(
    private val service : ChatService
) : ChatRepository {

    override suspend fun postarChat(id: Int, mensagem: String, tipo: String, imagem: String): Response<ChatPost> {
        return service.postarChat(id, mensagem, tipo, imagem)
    }

    override suspend fun abrirChat(id: Int, tipo: String): Response<ChatOpen> {
        return service.abrirChat(id, tipo)
    }

    override suspend fun chatUserSide(): Response<ChatUserSide> {
        return service.chatUserSide()
    }

    override suspend fun chatBarbeariaSide(): Response<ChatBarbeariaSide> {
        return service.chatBarbeariaSide()
    }


}