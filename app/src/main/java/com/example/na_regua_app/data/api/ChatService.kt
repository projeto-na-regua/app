package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.ChatBarbeariaSide
import com.example.na_regua_app.data.model.ChatOpen
import com.example.na_regua_app.data.model.ChatPost
import com.example.na_regua_app.data.model.ChatUserSide
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatService {
    @POST("/chat")
    suspend fun postarChat(
        @Query("id") id: Int,
        @Query("mensagem") mensagem: String,
        @Query("tipo") tipo: String,
        @Body imagem: String
    ): Response<ChatPost>

    @GET("/chat/open-chat")
    suspend fun abrirChat(
        @Query("id") id: Int,
        @Query("tipo") tipo: String
    ) : Response<ChatOpen>

    @GET("/chat/user-side/all")
    suspend fun chatUserSide() : Response<ChatUserSide>

    @GET("/chat/barbearia-side/all")
    suspend fun chatBarbeariaSide() : Response<ChatBarbeariaSide>

}