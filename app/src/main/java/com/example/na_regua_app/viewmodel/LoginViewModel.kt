package com.example.na_regua_app.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.DadosCadastroBarbearia
import com.example.na_regua_app.data.model.DadosLogin
import com.example.na_regua_app.data.repository.UsuarioRepository
import com.example.na_regua_app.utils.salvarToken
import kotlinx.coroutines.launch



class LoginViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {
    var email = mutableStateOf("")
    var senha = mutableStateOf("")

    fun atualizarEmail(novoEmail: String) {
        email.value = novoEmail
    }

    fun atualizarSenha(novaSenha: String) {
        senha.value = novaSenha
    }

    fun logar(context: Context, onResult: (Boolean) -> Unit) {

        val dadosLogin = DadosLogin(
            email = email.value,
            senha = senha.value,
        )

        viewModelScope.launch {
            try {
                val response = usuarioRepository.logar(dadosLogin)
                if (response.isSuccessful) {
                    response.body()?.let { salvarToken(context, token = it) }
                }
                onResult(response.isSuccessful)
            } catch (e: Exception) {
                onResult(false)
                Log.e("LoginViewModel", "Erro ao logar: ${e.message}")
            }
        }
    }
}




