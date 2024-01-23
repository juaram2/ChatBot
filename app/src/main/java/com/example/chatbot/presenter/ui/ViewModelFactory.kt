package com.example.chatbot.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatbot.domain.ChatListUseCase
import com.example.chatbot.domain.ChatReqUseCase

class ViewModelFactory(
    private val chatReqUseCase: ChatReqUseCase,
    private val chatListUseCase: ChatListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(chatReqUseCase, chatListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}