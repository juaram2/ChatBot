package com.example.chatbot.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.databinding.ItemChatMessageBinding
import com.example.chatbot.data.model.ChatMessage

class ChatListAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var data: List<ChatMessage>? = null
    fun setData(data: List<ChatMessage>) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}