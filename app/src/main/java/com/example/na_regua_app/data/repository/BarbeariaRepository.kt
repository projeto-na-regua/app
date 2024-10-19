package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.Usuario
import retrofit2.Response

interface BarbeariaRepository {
    suspend fun obterBarbearia() : Response<Barbearia>
}