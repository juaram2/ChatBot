package com.example.chatbot.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.example.chatbot.data.model.ChatReq
import com.example.chatbot.data.model.ResponseMessage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

interface ChatApiService {

    @POST("/api/generate")
    suspend fun requestMessage(@Body chatReq: ChatReq): Response<ResponseMessage>

    companion object {
        private val TAG = "ChatApiClient"
        private val BASE_URL = "http://ollama.confitech.co.kr:11111"

        private val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i(TAG, "okHttpClientBuilder: $message")
            }
        }).apply {
//            level = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor.Level.BODY
//            } else {
//                HttpLoggingInterceptor.Level.NONE
//            }
        }

        private val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                chain.proceed(request.build())
            }
            .addInterceptor(logger)
            .build()

        private val gson = GsonBuilder().registerTypeAdapter(
            LocalDateTime::class.java,
            JsonDeserializer { json, _, _ ->
                LocalDateTime.parse(
                    json.asString,
                    DateTimeFormatter.ISO_OFFSET_DATE_TIME
                )
            }).create()

        fun create() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ChatApiService::class.java)
    }
}