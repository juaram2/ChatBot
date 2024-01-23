package com.example.chatbot.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatbot.data.model.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM ChatMessage")
    fun getAll(): Flow<List<ChatMessage>>

    @Insert
    suspend fun insertAll(chat: ChatMessage)
}