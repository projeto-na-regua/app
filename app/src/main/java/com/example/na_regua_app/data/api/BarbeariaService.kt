package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Barbearia
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BarbeariaService {

    @GET("barbearias/perfil")
    suspend fun obterBarbearia(idBarbearia: Int): Response<Barbearia>

    @GET("barbearias/client-side/perfil/{idBarbearia}")
    suspend fun obterBarbeariaCliente(
        @Path("idBarbearia") idBarbearia: Int
    ) : Response<Barbearia>
}