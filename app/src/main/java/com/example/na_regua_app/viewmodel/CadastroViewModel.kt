package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class CadastroViewModel(
    private val usuarioRepository: UsuarioRepository
): ViewModel() {

//    var apelido = mutableStateOf("")
    var nome = mutableStateOf("")
    var email = mutableStateOf("")
    var senha = mutableStateOf("")
    var celular = mutableStateOf("")
//    var imgPerfil = mutableStateOf("")
    var cep = mutableStateOf("")
    var logradouro = mutableStateOf("")
    var numero = mutableStateOf("")
//    var complemento = mutableStateOf("")
    var cidade = mutableStateOf("")
    var estado = mutableStateOf("")
    var username = mutableStateOf("")
    var imagem = mutableStateOf("")

//    fun atualizarApelido(novoApelido: String){
//        apelido.value = novoApelido
//    }

    fun atualizarNome(novoNome: String) {
        nome.value = novoNome
    }

    fun atualizarEmail(novoEmail: String) {
        email.value = novoEmail
    }

    fun atualizarSenha(novaSenha: String) {
        senha.value = novaSenha
    }

    fun atualizarCelular(novoCelular: String) {
        celular.value = novoCelular
    }

//    fun atualizarImgPerfil(novaImgPerfil: String) {
//        imgPerfil.value = novaImgPerfil
//    }

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

    fun atualizarUsername(novoUsername: String) {
        username.value = novoUsername
    }

    fun atualizarImagem(novaImagem: String) {
        imagem.value = novaImagem
    }


    fun enviarCadastro(imagemFile: File?, onResult: (Boolean) -> Unit) {
        val dadosCadastro = DadosCadastro(
            nome = "João da Silva",
            email = "joao.silva@example.com",
            senha = "senha1234",
            celular = "11987654321",
            cep = "05882000",
            logradouro = "Rua Henrique Sam Mindlin",
            numero = "1263",
            cidade = "São Paulo",
            estado = "SP",
            username = "joaodasilva",
            imagemPerfil = imagem.value
        )

        viewModelScope.launch {
            try {
                // Serializar o JSON do objeto `dadosCadastro`
                val userJson = Gson().toJson(dadosCadastro)
                    .toRequestBody("application/json".toMediaTypeOrNull())

                // Preparar a imagem como `MultipartBody.Part` (caso exista)
                val imagemPart = if (imagemFile != null) {
                    MultipartBody.Part.createFormData(
                        "imagem",
                        imagemFile.name,
                        imagemFile.asRequestBody("image/*".toMediaTypeOrNull())
                    )
                } else {
                    null
                }

                // Enviar a requisição ao repositório com `userJson` e `imagemPart`
                val response = usuarioRepository.cadastrarUsuario(userJson, imagemPart)
                onResult(response.isSuccessful) // Verificar se a resposta foi bem-sucedida
            } catch (e: Exception) {
                onResult(false)
                Log.e("CadastroViewModel", "Erro ao enviar cadastro: ${e.message}")
            }
        }
    }


}