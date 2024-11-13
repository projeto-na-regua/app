package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Barbearia
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BarbeariaService {

    @GET("api/barbearias/perfil")
    suspend fun obterBarbearia(): Response<Barbearia>

    @GET("api/barbearias/client-side/perfil/{idBarbearia}")
    suspend fun obterBarbeariaCliente(
        @Path("idBarbearia") idBarbearia: Int?
    ) : Response<Barbearia>

    @PUT("api/barbearias/perfil")
    suspend fun atualizarBarbearia(
        @Body nvBarbearia: Barbearia
    ) : Response<Barbearia>
}