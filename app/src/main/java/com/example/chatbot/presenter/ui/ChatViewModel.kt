package com.example.chatbot.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.data.model.ChatMessage
import com.example.chatbot.domain.ChatListUseCase
import com.example.chatbot.domain.ChatReqUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatReqUseCase: ChatReqUseCase,
    private val chatListUseCase: ChatListUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState = _uiState.asStateFlow()

    val getMessages = chatListUseCase.getList

    fun sendMessage(message: String) {
        _isLoading.value = true
        saveMessage(message, false)
        viewModelScope.launch {
            chatReqUseCase.invoke(message)
                .catch { error ->
                    _uiState.value = UiState.Error(error.localizedMessage)
                }
                .collect {
                    saveMessage(it.response, true)
                }
        }
    }

    private fun saveMessage(
        message: String,
        isReceived: Boolean,
//        createdAt: String = LocalDateTime.now(ZoneId.systemDefault()).toString(),
    ) {
//        val createdAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH))
        viewModelScope.launch {
            chatListUseCase.invoke(message, isReceived)
                .catch {
                    error -> _uiState.value = UiState.Error(error.localizedMessage)
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                    _isLoading.value = false
                }
        }
    }
}

sealed class UiState {
    object Empty : UiState()
    data class Success(val message: List<ChatMessage>) : UiState()
    data class Error(val message: String?) : UiState()
}