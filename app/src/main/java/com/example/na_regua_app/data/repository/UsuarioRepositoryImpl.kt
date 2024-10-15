package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.UsuarioService
import com.example.na_regua_app.data.model.Usuario
import retrofit2.Response

class UsuarioRepositoryImpl(
    private val service : UsuarioService
) : UsuarioRepository {

    override suspend fun obterUsuario() : Response<Usuario> {
        return service.obterUsuario()
    }
}
