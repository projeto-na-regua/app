package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PesquisaService {

    @GET("api/pesquisa/client-side")
    suspend fun listarBarbeariasWToken(
        @Header("Authorization") token: String,
        @Query("servico") servico: String,
        @Query("date") data: String,
        @Query("time") hora: String,
        @Query("raio") raio: Int
    ): Response<List<BarbeariaPesquisa>>

    @GET("api/pesquisa/client-side/filtro")
    suspend fun listarBarbeariasPorNome(
        @Header("Authorization") token: String,
        @Query("nomeBarbearia") nomeBarbearia: String
    ) : Response<List<BarbeariaPesquisa>>

}