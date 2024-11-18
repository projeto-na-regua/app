package com.example.na_regua_app.data.repository

import android.provider.Telephony.Mms.Part
import com.example.na_regua_app.data.model.BarbeariaPesquisa
import com.example.na_regua_app.data.model.GaleriaConsulta
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface GaleriaRepository {

    suspend fun postarImagem(
        imagem: MultipartBody.Part?,
        descricao: RequestBody
    ) : Response<GaleriaConsulta>

    suspend fun getImages(): Response<List<GaleriaConsulta>>

}