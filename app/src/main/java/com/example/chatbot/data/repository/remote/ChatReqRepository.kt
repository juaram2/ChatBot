package com.example.chatbot.data.repository.remote

import com.example.chatbot.data.model.ChatReq
import com.example.chatbot.data.model.ResponseMessage
import kotlinx.coroutines.flow.Flow

interface ChatReqRepository {
    suspend fun requestMessage(req: ChatReq): Flow<ResponseMessage>
}