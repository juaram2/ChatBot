package com.example.chatbot.presenter.adapter

import android.graphics.Color
import android.view.Gravity
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.databinding.ItemChatMessageBinding
import com.example.chatbot.data.model.ChatMessage

class ViewHolder(private val binding: ItemChatMessageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChatMessage) {
        binding.txtMessage.text = item.message
        if (item.isReceived) {
            binding.layoutMessage.gravity = Gravity.START
            binding.messageBox.setCardBackgroundColor(Color.WHITE)
            binding.txtMessage.setTextColor(Color.BLACK)
        } else {
            binding.layoutMessage.gravity = Gravity.END
            binding.messageBox.setCardBackgroundColor(Color.parseColor("#747679"))
            binding.txtMessage.setTextColor(Color.WHITE)
        }
    }
}