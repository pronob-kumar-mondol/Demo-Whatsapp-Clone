package com.example.whatsappclone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.Data.DemoData
import com.example.whatsappclone.R
import de.hdodenhof.circleimageview.CircleImageView

class Chats_Adapter(private val chatList: List<DemoData>,
                    private val clickListener: OnItemClickListener):
    RecyclerView.Adapter<Chats_Adapter.ViewHolder>() {



    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val imageView: CircleImageView=itemView.findViewById(R.id.user_image)
        val user_name:TextView=itemView.findViewById(R.id.user_name)
        val last_msg:TextView=itemView.findViewById(R.id.last_msg)
        val time:TextView=itemView.findViewById(R.id.time)
    }


    interface OnItemClickListener{
        fun onItemClick(item: DemoData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Chats_Adapter.ViewHolder {
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item_card,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Chats_Adapter.ViewHolder, position: Int) {
        val currentItem=chatList[position]
        holder.imageView.setImageResource(currentItem.image)
        holder.user_name.text=currentItem.name
        holder.last_msg.text=currentItem.lastMsg
        holder.time.text=currentItem.time
        holder.itemView.isClickable=true

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}