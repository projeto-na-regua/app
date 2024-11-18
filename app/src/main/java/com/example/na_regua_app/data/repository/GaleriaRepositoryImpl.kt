package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.GaleriaService
import com.example.na_regua_app.data.model.GaleriaConsulta
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class GaleriaRepositoryImpl(
    private val service: GaleriaService
) : GaleriaRepository {

    override suspend fun postarImagem(
        imagem: MultipartBody.Part?,
        descricao: RequestBody,
    ): Response<GaleriaConsulta> {
        return service.postarImagem(imagem, descricao)
    }

    override suspend fun getImages(): Response<List<GaleriaConsulta>> {
        return service.getImages()
    }

}