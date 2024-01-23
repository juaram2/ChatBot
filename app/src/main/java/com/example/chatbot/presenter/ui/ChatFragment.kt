package com.example.chatbot.presenter.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.databinding.FragmentChatBinding
import com.example.chatbot.Injection
import com.example.chatbot.presenter.adapter.ChatListAdapter

class ChatFragment : Fragment() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = Injection.provideViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ChatViewModel::class.java)

        bind()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bind() {
        binding.btnSendMsg.setOnClickListener {
            val message = binding.userMsg.text.toString()
            if (message.isNotBlank()) {
                viewModel.sendMessage(message)
                binding.userMsg.text.clear()
            } else {
                Toast.makeText(requireContext(), "메세지를 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }

        val chatListAdapter = ChatListAdapter()
        val chatListLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.chatList.apply {
            setHasFixedSize(true)
            adapter = chatListAdapter
            layoutManager = chatListLayoutManager
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getMessages.collect {
                if (it.isNotEmpty()) {
                    chatListAdapter.setData(it)
                    binding.chatList.scrollToBottom(it.size - 1)
                    binding.txtEmpty.visibility = View.GONE
                } else {
                    binding.txtEmpty.visibility = View.VISIBLE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it) {
                    is UiState.Success -> {
                        chatListAdapter.setData(it.message)
                        chatListAdapter.notifyDataSetChanged()
                        binding.chatList.scrollToBottom(it.message.size - 1)
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect { isLoading ->
                binding.loadingBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    private fun RecyclerView.scrollToBottom(lastPosition: Int) {
        this.scrollToPosition(lastPosition)
    }

    companion object {
        fun newInstance() = ChatFragment()
    }
}