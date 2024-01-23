package com.example.chatbot.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class ChatMessage(
    @PrimaryKey val uid: UUID,
    @SerializedName("message") val message: String,
    @SerializedName("is_received") val isReceived: Boolean,
//    @SerializedName("created_at") val createdAt: LocalDateTime,
)
