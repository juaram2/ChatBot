package com.example.chatbot

import android.content.Context
import com.example.chatbot.data.ChatApiService
import com.example.chatbot.data.db.AppDatabase
import com.example.chatbot.data.repository.local.ChatDao
import com.example.chatbot.data.repository.local.ChatMessagesRepository
import com.example.chatbot.data.repository.remote.ChatReqRepository
import com.example.chatbot.data.repository.remote.ChatReqRepositoryImpl
import com.example.chatbot.domain.ChatListUseCase
import com.example.chatbot.domain.ChatReqUseCase
import com.example.chatbot.presenter.ui.ViewModelFactory

object Injection {
    /** Remote */
    private fun provideChatService(): ChatApiService {
        return ChatApiService.create()
    }
    private fun provideChatReqRepository(): ChatReqRepository {
        val chatApiService = provideChatService()
        return ChatReqRepositoryImpl(chatApiService)
    }
    private fun provideChatReqUseCase(): ChatReqUseCase {
        val chatReqRepository = provideChatReqRepository()
        return ChatReqUseCase(chatReqRepository)
    }

    /** Local */
    private fun provideChatDataSource(context: Context): ChatDao {
        val database = AppDatabase.getInstance(context)
        return database.chatDao()
    }
    private fun provideChatMessagesRepository(context: Context): ChatMessagesRepository {
        val chatDao = provideChatDataSource(context)
        return ChatMessagesRepository(chatDao)
    }
    private fun provideChatListUseCase(context: Context) : ChatListUseCase {
        val chatMessagesRepository = provideChatMessagesRepository(context)
        return ChatListUseCase(chatMessagesRepository)
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val chatReqUseCase = provideChatReqUseCase()
        val chatListUseCase = provideChatListUseCase(context)
        return ViewModelFactory(chatReqUseCase, chatListUseCase)
    }
}