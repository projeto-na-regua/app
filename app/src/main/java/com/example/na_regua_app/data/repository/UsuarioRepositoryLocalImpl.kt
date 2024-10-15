package com.example.na_regua_app.data.repository

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
}