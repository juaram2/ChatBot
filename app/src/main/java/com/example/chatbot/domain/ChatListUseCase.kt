package com.example.chatbot.domain

import com.example.chatbot.data.model.ChatMessage
import com.example.chatbot.data.repository.local.ChatMessagesRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ChatListUseCase(
    private val chatMessagesRepository: ChatMessagesRepository
) {
    val getList: Flow<List<ChatMessage>> = chatMessagesRepository.messages

    suspend operator fun invoke(
        message: String,
        isReceived: Boolean,
//        createdAt: LocalDateTime,
    ): Flow<List<ChatMessage>> {
        val item = ChatMessage(
            uid = UUID.randomUUID(),
            message = message,
            isReceived = isReceived,
//            createdAt = createdAt
        )
        chatMessagesRepository.insert(item)

        return chatMessagesRepository.messages
    }
}