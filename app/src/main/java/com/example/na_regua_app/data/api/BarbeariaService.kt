package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Barbearia
import retrofit2.Response
import retrofit2.http.GET

interface BarbeariaService {

    @GET("barbearias/perfil")
    suspend fun obterBarbearia(
    ) : Response<Barbearia>
}