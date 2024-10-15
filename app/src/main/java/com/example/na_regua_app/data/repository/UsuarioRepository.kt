package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Usuario
import retrofit2.Response

interface UsuarioRepository {
    suspend fun obterUsuario() : Response<Usuario>
}