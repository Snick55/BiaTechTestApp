package com.example.biatechtestapp.presentation.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.biatechtestapp.databinding.ChatItemBinding
import com.example.biatechtestapp.presentation.chat.entities.ChatUiItem


class ChatAdapter: RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private var chats: List<ChatUiItem> = emptyList()

    fun setUpAdapter(list: List<ChatUiItem>){
        val diffUtilCallback = Callback(chats, list)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        chats = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatItemBinding.inflate(inflater,parent,false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount(): Int = chats.size

    inner class ChatViewHolder(private val binding: ChatItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(currentChat: ChatUiItem){
            with(binding){
                Glide.with(binding.root.context)
                    .load(currentChat.avatarUrl)
                    .circleCrop()
                    .into(avatar)

                name.text = currentChat.name
                lastMessage.text = currentChat.lastMessage

                if (currentChat.countOfMessage < 1){
                    countCardView.visibility = View.INVISIBLE
                    timeOfLastMessage.visibility = View.INVISIBLE
                }
                timeOfLastMessage.text = currentChat.timeOfLastMessage
                countMessage.text = currentChat.countOfMessage.toString()
            }
        }
    }

    private class Callback(
        private val oldList: List<ChatUiItem>,
        private val newList: List<ChatUiItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldChat = oldList[oldItemPosition]
            val newChat = newList[newItemPosition]
            return oldChat.id == newChat.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldChat = oldList[oldItemPosition]
            val newChat = newList[newItemPosition]
            return oldChat == newChat
        }
    }


}