package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface UsuarioService {

    @GET("usuarios/perfil")
    suspend fun obterUsuario(
    ) : Response<Usuario>

    @POST("usuarios/cadastro")
    suspend fun cadastrarUsuario(@Body dados: DadosCadastro) : Response<String>

}