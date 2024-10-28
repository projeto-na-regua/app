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
    var imgPerfil = mutableStateOf("")

    init {
        obterUsuario()
    }

       fun obterUsuario() {
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




    suspend fun editarPerfil(usuario: UsuarioDTOUpdate): Response<Void> {
        return usuarioRepository.editarPerfil(usuario)
    }
}
