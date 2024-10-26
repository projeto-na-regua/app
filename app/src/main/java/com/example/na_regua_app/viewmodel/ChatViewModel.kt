package com.example.na_regua_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.na_regua_app.data.model.*
import com.example.na_regua_app.data.repository.ChatRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {

    // LiveData para ChatPost
    private val _chatPostResponse = MutableLiveData<Response<ChatPost>>()
    val chatPostResponse: LiveData<Response<ChatPost>> = _chatPostResponse

    // LiveData para ChatOpen
    private val _chatOpenResponse = MutableLiveData<Response<ChatOpen>>()
    val chatOpenResponse: LiveData<Response<ChatOpen>> = _chatOpenResponse

    // LiveData para ChatUserSide
    private val _chatUserSideResponse = MutableLiveData<Response<ChatUserSide>>()
    val chatUserSideResponse: LiveData<Response<ChatUserSide>> = _chatUserSideResponse

    // LiveData para ChatBarbeariaSide
    private val _chatBarbeariaSideResponse = MutableLiveData<Response<ChatBarbeariaSide>>()
    val chatBarbeariaSideResponse: LiveData<Response<ChatBarbeariaSide>> = _chatBarbeariaSideResponse


    init {
        chatUserSide()
    }


    // Função para postar mensagem de chat
    fun postarChat(id: Int, mensagem: String, tipo: String, imagem: String) {
        viewModelScope.launch {
            val response = chatRepository.postarChat(id, mensagem, tipo, imagem)
            _chatPostResponse.value = response
        }
    }

    // Função para abrir chat
    fun abrirChat(id: Int, tipo: String) {
        viewModelScope.launch {
            val response = chatRepository.abrirChat(id, tipo)
            _chatOpenResponse.value = response
        }
    }

    // Função para carregar mensagens do lado do usuário
    fun chatUserSide() {
        viewModelScope.launch {
            val response = chatRepository.chatUserSide()
            _chatUserSideResponse.value = response
        }
    }

    // Função para carregar mensagens do lado da barbearia
    fun chatBarbeariaSide() {
        viewModelScope.launch {
            val response = chatRepository.chatBarbeariaSide()
            _chatBarbeariaSideResponse.value = response
        }
    }
}
