package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.model.UsuarioDTOUpdate
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.data.repository.UsuarioRepositoryLocalImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class PerfilUsuarioViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    var nome = mutableStateOf("")
    var email = mutableStateOf("")
    var senha = mutableStateOf("")
    var celular = mutableStateOf("")
    var cep = mutableStateOf("")
    var logradouro = mutableStateOf("")
    var numero = mutableStateOf("")
    var complemento = mutableStateOf("")
    var cidade = mutableStateOf("")
    var estado = mutableStateOf("")
    var username = mutableStateOf("")

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

    fun atualizarCep(novoCep: String) {
        cep.value = novoCep
    }

    fun atualizarLogradouro(novoLogradouro: String) {
        logradouro.value = novoLogradouro
    }

    fun atualizarNumero(novoNumero: String) {
        numero.value = novoNumero
    }

    fun atualizarComplemento(novoComplemento: String) {
        complemento.value = novoComplemento
    }

    fun atualizarCidade(novaCidade: String) {
        cidade.value = novaCidade
    }

    fun atualizarEstado(novoEstado: String) {
        estado.value = novoEstado
    }

    fun atualizarUsername(novoUsername: String) {
        username.value = novoUsername
    }

    init {
        obterUsuario()
    }

     private  fun obterUsuario() {
        viewModelScope.launch {
            val response = usuarioRepository.obterUsuario()
            println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            println(response.body())
            if (response.isSuccessful) {
                _usuario.value = response.body()

            } else {

            }
        }
    }

    fun obterUsuarioBody(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = usuarioRepository.obterUsuario()

                if (response.isSuccessful) {
                    // Supondo que você quer armazenar o usuário em algum lugar
                    _usuario.value = response.body() // Salve o usuário, se necessário
                    onResult(true) // Chame o callback com sucesso
                } else {
                    Log.e("PerfilUsuarioViewModel", "Erro: $response")
                    onResult(false) // Chame o callback com erro
                }
            } catch (e: Exception) {
                onResult(false) // Chame o callback com erro
                Log.e("PerfilUsuarioViewModel", "Erro ao obter usuario: ${e.message}")
            }
        }
    }


    suspend fun editarPerfil(usuario: UsuarioDTOUpdate): Response<Void> {
        return usuarioRepository.editarPerfil(usuario)
    }
}
