package com.example.chatbot.data.model

import com.google.gson.annotations.SerializedName

data class ChatReq(
    @SerializedName("model") val model: String = "llama2",
    @SerializedName("prompt") val prompt: String,
    @SerializedName("stream") val stream: Boolean = false,
)
