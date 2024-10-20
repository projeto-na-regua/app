package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.Usuario
import retrofit2.Response

interface UsuarioRepository {
    suspend fun obterUsuario() : Response<Usuario>

    suspend fun cadastrarUsuario(dadosCadastro: DadosCadastro): Response<String>
}