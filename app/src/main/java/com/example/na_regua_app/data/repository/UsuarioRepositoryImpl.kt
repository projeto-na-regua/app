package com.example.na_regua_app.data.repository

import com.example.na_regua_app.data.api.UsuarioService
import com.example.na_regua_app.data.model.Barbearia
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.model.UserDType
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.UsuarioDTOUpdate
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class UsuarioRepositoryImpl(
    private val service : UsuarioService
) : UsuarioRepository {

    override suspend fun obterUsuario() : Response<Usuario> {
        return service.obterUsuario()
    }

    override suspend fun cadastrarUsuario(userJson: RequestBody, imagem: MultipartBody.Part?): Response<String>{
        return service.cadastrarUsuario(userJson, imagem)
    }


    override suspend fun cadastrarBarbearia(barbearia: RequestBody, perfil: MultipartBody.Part?, banner: MultipartBody.Part?) : Response<Void> {
        return service.cadastrarBarbearia("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjIwIiwibm9tZSI6Ikpvw6NvIGRhIFNpbHZhIiwic2VuaGEiOiJzZW5oYTEyMzQiLCJlbWFpbCI6ImpvYW8uc2lsdmFAZXhhbXBsZS5jb20iLCJleHAiOjE3Mjk5MDM1MTkxNzl9.7mQIMpRyXFlUzNot13H27P24yag89_gO7MI9Vsm25Lc", barbearia, perfil, banner)
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
