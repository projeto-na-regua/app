package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.BarbeariaDTO
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.UsuarioDTOUpdate
import retrofit2.Response

interface BarbeariaRepository {
    suspend fun editarPerfil(barbearia: BarbeariaDTO): Response<Void>
    suspend fun obterBarbearia(idBarbearia: Int) : Response<Barbearia>

    suspend fun obterBarbeariaCliente(idBarbearia: Int) : Response<Barbearia>
}