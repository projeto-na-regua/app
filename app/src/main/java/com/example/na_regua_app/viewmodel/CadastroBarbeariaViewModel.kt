package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.DadosCadastro
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.repository.UsuarioRepository
import kotlinx.coroutines.launch

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


    fun enviarCadastroBarbearia(onResult: (Boolean) -> Unit) {
        val dadosCadastro = DadosCadastroBarbearia(
            nomeDoNegocio = nomeDoNegocio.value,
            cpf = cpf.value,
            cep = cep.value,
            logradouro = logradouro.value,
            numero = numero.value,
            cidade = cidade.value,
            estado = estado.value
        )

        viewModelScope.launch {
            try {
                val response = usuarioRepository.cadastrarBarbearia(dadosCadastro)

                if (response.isSuccessful) {
                    onResult(true) // Cadastro bem-sucedido
                } else {
                    Log.e("CadastroBarbeariaViewModel", "Erro: $response")
                    onResult(false) // Cadastro falhou
                }
            } catch (e: Exception) {
                onResult(false)
                Log.e("CadastroBarbeariaViewModel", "Erro ao enviar cadastro: ${e.message}")
            }
        }
    }
}