package com.example.chatbot.data.model

import com.google.gson.annotations.SerializedName

data class ResponseMessage(
    @SerializedName("model") val model: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("response") val response: String,
    @SerializedName("done") val done: Boolean,
    @SerializedName("context") val context: List<Int>,
    @SerializedName("total_duration") val totalDuration: Long,
    @SerializedName("load_duration") val loadDuration: Long,
    @SerializedName("prompt_eval_duration") val promptEvalDuration: Long,
    @SerializedName("eval_count") val evalCount: Int,
    @SerializedName("eval_duration") val evalDuration: Long,
)