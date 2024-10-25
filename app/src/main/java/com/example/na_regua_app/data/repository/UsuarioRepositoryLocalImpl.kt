package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.model.UserDType
import com.example.na_regua_app.data.model.Usuario
import retrofit2.Response

class UsuarioRepositoryLocalImpl() : UsuarioRepository {
    override suspend fun obterUsuario(): Response<Usuario> {
        return Response.success(
                Usuario(
                    nome = "@JoaoSilva186",
                    email = "joao.silva@gmail.com",
                    senha = "senhaSecreta123",
                    celular = "+55 11 91234-5678",
                    imgPerfil = "https://example.com/img/perfil-joao.jpg",
                    cep = "12345-678",
                    logradouro = "Rua das Flores",
                    numero = 123,
                    complemento = "Apto 202",
                    cidade = "São Paulo",
                    estado = "SP",
                    dtype = "Cliente"
                )
        )
    }

    override suspend fun cadastrarUsuario(dadosCadastro: DadosCadastro): Response<String> {
        TODO("Not yet implemented")
    }

    override suspend fun cadastrarBarbearia(dadosCadastradosBarbearia: DadosCadastroBarbearia): Response<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun logar(dadosLogin: DadosLogin): Response<String> {
        TODO("Not yet implemented")
    }

    override suspend fun admIsTrue(token: String): Response<UserDType> {
        TODO("Not yet implemented")
    }

    override suspend fun editarPerfil(usuario: Usuario): Response<Void> {
        TODO("Not yet implemented")
    }


}