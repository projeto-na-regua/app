package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.UsuarioService
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.model.UserDType
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.UsuarioDTOUpdate
import retrofit2.Response

class UsuarioRepositoryImpl(
    private val service : UsuarioService
) : UsuarioRepository {

    override suspend fun obterUsuario() : Response<Usuario> {
        return service.obterUsuario()
    }

    override suspend fun cadastrarUsuario(dadosCadastro: DadosCadastro): Response<String> {
        return service.cadastrarUsuario(dadosCadastro)
    }

    override suspend fun cadastrarBarbearia(dadosCadastradosBarbearia: DadosCadastroBarbearia) : Response<Void> {
        return service.cadastrarBarbearia("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE4Iiwibm9tZSI6IkRvdWdsYXMgUXVlaXJveiIsInNlbmhhIjoic2VuaGExMTEiLCJlbWFpbCI6ImRvdWdsYXNAZ21haWwuY29tIiwiZXhwIjoxNzI5NDczMDY5OTMzfQ.x2j66-wsxG815js5eGKhXsLH998k2WaL8SJcJR7uLcs", dadosCadastradosBarbearia)
    }

    override suspend fun logar(dadosLogin: DadosLogin): Response<String> {
        return service.logar(dadosLogin)
    }

    override suspend fun admIsTrue(token: String): Response<UserDType> {
        return service.admIsTrue(token)
    }

    override suspend fun editarPerfil(usuario: UsuarioDTOUpdate): Response<Void> {
       return service.editarPerfil(usuario)
    }

//    override suspend fun editarImagemPerfil(file: String): Boolean {
//        TODO("Not yet implemented")
//    }

}
