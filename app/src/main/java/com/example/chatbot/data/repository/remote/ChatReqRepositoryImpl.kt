package com.example.chatbot.data.repository.remote

import com.example.chatbot.data.ChatApiService
import com.example.chatbot.data.model.ChatReq
import com.example.chatbot.data.model.ResponseMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatReqRepositoryImpl(private val service: ChatApiService) : ChatReqRepository {
    override suspend fun requestMessage(req: ChatReq): Flow<ResponseMessage> = flow {
        val result = service.requestMessage(req).body()!! // FIXME
        emit(result)
    }
}