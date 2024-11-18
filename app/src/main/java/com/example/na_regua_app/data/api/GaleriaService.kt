package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.GaleriaConsulta
import com.example.na_regua_app.data.model.Servico
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface GaleriaService {

    @Multipart
    @POST("galeria")
    suspend fun postarImagem(
        @Part imagem: MultipartBody.Part?,
        @Part("descricao") descricao: RequestBody
    ): Response<GaleriaConsulta>

    @GET("galeria")
    suspend fun getImages() : Response<List<GaleriaConsulta>>



}