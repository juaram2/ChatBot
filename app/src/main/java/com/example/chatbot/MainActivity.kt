package com.example.chatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatbot.presenter.ui.ChatFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance())
                .commitNow()
        }
    }
}