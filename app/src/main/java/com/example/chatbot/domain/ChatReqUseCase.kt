package com.example.chatbot.domain

import com.example.chatbot.data.model.ChatReq
import com.example.chatbot.data.model.ResponseMessage
import com.example.chatbot.data.repository.remote.ChatReqRepository
import kotlinx.coroutines.flow.Flow

class ChatReqUseCase(
    private val chatReqRepository: ChatReqRepository,
) {
    suspend operator fun invoke(message: String): Flow<ResponseMessage> {
        val req = ChatReq(prompt = message)
        return chatReqRepository.requestMessage(req)
    }
}