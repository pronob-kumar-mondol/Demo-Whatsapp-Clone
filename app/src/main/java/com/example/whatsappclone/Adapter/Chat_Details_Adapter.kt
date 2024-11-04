package com.example.whatsappclone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.Data.Message
import com.example.whatsappclone.R

class Chat_Details_Adapter(
    private val messages: List<Message>,
    private val itemClickListener: OnItemClickListener
): RecyclerView.Adapter<Chat_Details_Adapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(message: Message)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.text_content)
        val messageTime: TextView = view.findViewById(R.id.text_time)
        val parentLayout: View = view.findViewById(R.id.lyt_parent)

        init {
            // Set the click listener on the parent layout
            parentLayout.setOnClickListener {
                itemClickListener.onItemClick(messages[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = if (viewType == 1) {
            R.layout.item_chat_whatsapp_me // Layout for sent messages
        } else {
            R.layout.item_chat_whatsapp_telegram_you // Layout for received messages
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.content
        holder.messageTime.text = message.timestamp // Displaying timestamp
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSent) 1 else 0
    }
}