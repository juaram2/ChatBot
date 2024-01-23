package com.example.chatbot.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.time.LocalDateTime

class DateTimeConverter {
    @TypeConverter
    fun dateTimeToJson(value: LocalDateTime?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToDateTime(value: String): LocalDateTime? {
        return Gson().fromJson(value, LocalDateTime::class.java)
    }
}