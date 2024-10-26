package com.example.na_regua_app.data.api

import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.model.UserDType
import com.example.na_regua_app.data.model.Usuario
import com.github.tehras.charts.bar.BarChartData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.PUT


interface UsuarioService {

    @GET("usuarios/perfil")
    suspend fun obterUsuario(
    ) : Response<Usuario>

    @Multipart
    @POST("usuarios/cadastro")
    suspend fun cadastrarUsuario(
        @Part("user") userJson: RequestBody,
        @Part imagem: MultipartBody.Part?
    ): Response<String>

    @Multipart
    @POST("usuarios/cadastro-barbearia")
    suspend fun cadastrarBarbearia(
        @Header("Authorization") token: String,
        @Part("barbearia") barbearia: RequestBody,
        @Part perfil: MultipartBody.Part?,
        @Part banner: MultipartBody.Part?
    ): Response<Void>

    @POST("usuarios")
    suspend fun logar(
        @Body dadosLogin: DadosLogin
    ) : Response<String>

    @GET("usuarios/user")
    suspend fun admIsTrue(
        @Header("Authorization") token: String
    ) : Response<UserDType>

    // Novo método para editar o perfil do usuário
    @PUT("usuarios/editar-perfil")
    suspend fun editarPerfil(@Body usuario: Usuario): Response<Void>

//    // Novo método para editar a imagem de perfil do usuário
//    @Multipart
//    @POST("usuarios/editar-img-perfil")
//    suspend fun editarImagemPerfil(@Part file: String): Response<Void>
}
