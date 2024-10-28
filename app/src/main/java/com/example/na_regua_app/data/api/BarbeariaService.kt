package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.BarbeariaDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.PUT

interface BarbeariaService {

    @GET("barbearias/perfil")
    suspend fun obterBarbearia(): Response<Barbearia>

    @GET("barbearias/client-side/perfil/{idBarbearia}")
    suspend fun obterBarbeariaCliente(
        @Path("idBarbearia") idBarbearia: Int
    ) : Response<Barbearia>
  
    suspend fun obterBarbearia() : Response<Barbearia>

    @PUT("barbearias/perfil")
    suspend fun editarPerfil(@Body barbearia: BarbeariaDTO) : Response<Void>

    @PUT("barbearias/img-perfil")
    suspend fun editarImgPerfil() : Response<Barbearia>

    @PUT("barbearias/img-banner")
    suspend fun editarImgBannerPerfil() : Response<Barbearia>

    @GET("barbearias/client-side/pesquisa")
    suspend fun pesquisaBarbearia() : Response<Barbearia>

//    @GET("barbearias/client-side/perfil")
//    suspend fun pesquisaBarbearia() : Response<Barbearia>


    @PUT("barbearias/perfil")
    suspend fun atualizarBarbearia(
        @Body nvBarbearia: Barbearia
    ) : Response<Barbearia>

}