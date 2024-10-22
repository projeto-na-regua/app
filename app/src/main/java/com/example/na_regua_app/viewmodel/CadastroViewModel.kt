package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.repository.UsuarioRepository
import kotlinx.coroutines.launch

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


    fun enviarCadastro(onResult: (Boolean) -> Unit) {
        val dadosCadastro = DadosCadastro(
            nome = nome.value,
            email = email.value,
            senha = senha.value,
            celular = celular.value,
            cep = cep.value,
            logradouro = logradouro.value,
            numero = numero.value,
            cidade = cidade.value,
            estado = estado.value
        )

        viewModelScope.launch {
            try {
                // Certifique-se de que o retorno seja verificado
                val response = usuarioRepository.cadastrarUsuario(dadosCadastro)
                onResult(response.isSuccessful) // Verifique se a resposta foi bem-sucedida
            } catch (e: Exception) {
                onResult(false)
                // Log ou exiba a exceção para debugar
                Log.e("CadastroViewModel", "Erro ao enviar cadastro: ${e.message}")
            }
        }
    }


}