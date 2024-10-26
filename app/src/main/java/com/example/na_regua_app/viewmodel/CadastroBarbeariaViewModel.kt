package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class CadastroBarbeariaViewModel(
    private val usuarioRepository: UsuarioRepository
): ViewModel() {

    var nomeDoNegocio = mutableStateOf("")
    var descricao = mutableStateOf("")
    // var imgBanner = mutableStateOf("")
    //    var imgPerfil = mutableStateOf("")
    var cpf = mutableStateOf("")
    var cep = mutableStateOf("")
    var logradouro = mutableStateOf("")
    var numero = mutableStateOf("")
    //    var complemento = mutableStateOf("")
    var cidade = mutableStateOf("")
    var estado = mutableStateOf("")


fun atualizarNomeDoNegocio(novoNomeDoNegocio: String) {
        nomeDoNegocio.value = novoNomeDoNegocio
    }

    fun atualizarDescricao(novaDescricao: String) {
        descricao.value = novaDescricao
    }

//    fun atualizarImgBanner(novaImgBanner: String) {
//        imgBanner.value = novaImgBanner
//    }
//
//    fun atualizarImgPerfil(novaImgPerfil: String) {
//        imgPerfil.value = novaImgPerfil
//    }

    fun atualizarCpf(novoCpf: String) {
        cpf.value = novoCpf
    }

    fun atualizarCep(novoCep: String) {
        cep.value = novoCep
    }

    fun atualizarLogradouro(novoLogradouro: String) {
        logradouro.value = novoLogradouro
    }

    fun atualizarNumero(novoNumero: String) {
        numero.value = novoNumero
    }

//    fun atualizarComplemento(novoComplemento: String) {
//        complemento.value = novoComplemento
//    }

    fun atualizarCidade(novaCidade: String) {
        cidade.value = novaCidade
    }

    fun atualizarEstado(novoEstado: String) {
        estado.value = novoEstado
    }



    fun enviarCadastroBarbearia(imagemPerfilFile: File?, imagemBannerFile: File?, onResult: (Boolean) -> Unit) {
        val dadosCadastro = DadosCadastroBarbearia(
            nomeDoNegocio = "Barbearia do Joao",
            cpf = "889.920.880-86",
            cep = "05882-000",
            logradouro = "Rua Henrique Sam Mindlin",
            numero = "1265",
            cidade = "São Paulo",
            estado = "SP"
        )

        viewModelScope.launch {
            try {
                val barbeariaJson = Gson().toJson(dadosCadastro)
                    .toRequestBody("application/json".toMediaTypeOrNull())

                // Preparar a imagem de perfil como `MultipartBody.Part` (caso exista)
                val imagemPerfilPart = if (imagemPerfilFile != null) {
                    MultipartBody.Part.createFormData(
                        "perfil", // nome do parâmetro para o servidor
                        imagemPerfilFile.name,
                        imagemPerfilFile.asRequestBody("image/*".toMediaTypeOrNull())
                    )
                } else {
                    null
                }

                // Preparar a imagem de banner como `MultipartBody.Part` (caso exista)
                val imagemBannerPart = if (imagemBannerFile != null) {
                    MultipartBody.Part.createFormData(
                        "banner", // nome do parâmetro para o servidor
                        imagemBannerFile.name,
                        imagemBannerFile.asRequestBody("image/*".toMediaTypeOrNull())
                    )
                } else {
                    null
                }

                // Chamar o repositório para cadastrar a barbearia
                val response = usuarioRepository.cadastrarBarbearia(barbeariaJson, imagemPerfilPart, imagemBannerPart)

                if (response.isSuccessful) {
                    onResult(true) // Cadastro bem-sucedido
                } else {
                    Log.e("CadastroBarbeariaViewModel", "Erro: ${response.errorBody()?.string()}")
                    onResult(false) // Cadastro falhou
                }
            } catch (e: Exception) {
                onResult(false)
                Log.e("CadastroBarbeariaViewModel", "Erro ao enviar cadastro: ${e.message}")
            }
        }
    }


}