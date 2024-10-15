package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Usuario
import retrofit2.Response
import retrofit2.http.GET


interface UsuarioService {

    @GET("usuarios/perfil")
    suspend fun obterUsuario(
    ) : Response<Usuario>

}