package com.example.chatbot.data.repository.local

import com.example.chatbot.data.model.ChatMessage
import kotlinx.coroutines.flow.Flow

class ChatMessagesRepository(private val dao: ChatDao) {
    val messages: Flow<List<ChatMessage>> = dao.getAll()

    suspend fun insert(chatMessage: ChatMessage) {
        dao.insertAll(chatMessage)
    }
}