package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.model.UserDType
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.UsuarioDTOUpdate
import retrofit2.Response

interface UsuarioRepository {
    suspend fun obterUsuario(): Response<Usuario>

    suspend fun cadastrarUsuario(dadosCadastro: DadosCadastro): Response<String>

    suspend fun cadastrarBarbearia(dadosCadastradosBarbearia: DadosCadastroBarbearia): Response<Void>

    suspend fun logar(dadosLogin: DadosLogin): Response<String>

    suspend fun admIsTrue(token: String): Response<UserDType>

    suspend fun editarPerfil(usuario: UsuarioDTOUpdate): Response<Void>

//    suspend fun editarImagemPerfil(file: String): Boolean
}

