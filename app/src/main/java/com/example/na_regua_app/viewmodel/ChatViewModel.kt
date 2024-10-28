package com.example.na_regua_app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.*
import com.example.na_regua_app.data.repository.ChatRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    private val _chatPostResponse = MutableLiveData<Response<ChatPost>>()
    val chatPostResponse: LiveData<Response<ChatPost>> = _chatPostResponse

    private val _chatOpenResponse = MutableLiveData<Response<ChatOpen>>()
    val chatOpenResponse: LiveData<Response<ChatOpen>> = _chatOpenResponse

    private val _chatUserSideResponse = MutableLiveData<Response<ChatUserSide>>()
    val chatUserSideResponse: LiveData<Response<ChatUserSide>> = _chatUserSideResponse

    private val _chatBarbeariaSideResponse = MutableLiveData<Response<ChatBarbeariaSide>>()
    val chatBarbeariaSideResponse: LiveData<Response<ChatBarbeariaSide>> = _chatBarbeariaSideResponse

    private val _chatMessages = MutableLiveData<List<ChatPost>>() // Adiciona um LiveData para as mensagens
    val chatMessages: LiveData<List<ChatPost>> = _chatMessages

    fun postarChat(chatId: Int, mensagem: String, tipo: String, imagem: String) {
        viewModelScope.launch {
            val response = chatRepository.postarChat(chatId, mensagem, tipo, imagem)
            if (response.isSuccessful) {
                response.body()?.let { newMessage ->
                    // Atualiza a lista de mensagens
                    _chatMessages.value = _chatMessages.value?.plus(newMessage)

                    // Log para confirmar envio bem-sucedido
                    Log.d("ChatViewModel", "Mensagem enviada com sucesso: $mensagem, Tipo: $tipo, Imagem: $imagem")
                }
            } else {
                // Log para erros ao tentar enviar a mensagem
                Log.e("ChatViewModel", "Erro ao enviar mensagem: ${response.errorBody()?.string()}")
            }
        }
    }

    fun abrirChat(chatId: Int, tipo: String) {
        viewModelScope.launch {
            val response = chatRepository.abrirChat(chatId, tipo)
            _chatOpenResponse.value = response

            // Inicializa as mensagens do chat ao abrir
            _chatMessages.value = response.body()?.conteudoGeral ?: emptyList()
        }
    }

    fun chatUserSide() {
        viewModelScope.launch {
            val response = chatRepository.chatUserSide()
            _chatUserSideResponse.value = response
        }
    }


    fun chatBarbeariaSide() {
        viewModelScope.launch {
            val response = chatRepository.chatBarbeariaSide()
            _chatBarbeariaSideResponse.value = response
        }
    }
}

