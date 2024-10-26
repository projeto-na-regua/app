package com.example.na_regua_app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.Usuario
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.data.repository.UsuarioRepositoryLocalImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilUsuarioViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> get() = _usuario

    init {
        obterUsuario()
    }

    private fun obterUsuario() {
        viewModelScope.launch {
            try {
                val usuarioData = usuarioRepository.obterUsuario()
                if (usuarioData.isSuccessful) {
                    _usuario.value = usuarioData.body()
                    Log.d("PerfilUsuarioViewModel", "Dados do usuário: $usuarioData")
                } else {
                    Log.e("PerfilUsuarioViewModel", "Erro na resposta: ${usuarioData.code()}")
                    Log.e("PerfilUsuarioViewModel", "Cabeçalhos: ${usuarioData.headers()}")
                    Log.e("PerfilUsuarioViewModel", "Erro ao obter usuário: ${usuarioData.errorBody()?.string()}")
                    _usuario.value = null
                }
            } catch (e: Exception) {
                Log.e("PerfilUsuarioViewModel", "Erro ao obter usuário: ${e.message}")
                _usuario.value = null
            }
        }
    }
}