package com.example.whatsappclone

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsappclone.Activity.ChatActivity
import com.example.whatsappclone.Adapter.Chats_Adapter
import com.example.whatsappclone.Data.DemoData
import com.example.whatsappclone.databinding.FragmentChatBinding


class ChatFragment : Fragment(),Chats_Adapter.OnItemClickListener {

    private lateinit var binding: FragmentChatBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentChatBinding.inflate(inflater,container,false)





        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val demoData= listOf(
            DemoData(R.drawable.messi,"Sahadat","Whass UP?","10:00 AM"),
            DemoData(R.drawable.pogba,"Pogba","Working!","17:00 AM"),
            DemoData(R.drawable.meena,"Mina","Whass Down?","10:00 AM"),
            DemoData(R.drawable.icc,"Icc","UP?","1:00 AM")
        )

        binding.chatRcView.layoutManager=LinearLayoutManager(requireContext())
        binding.chatRcView.adapter=Chats_Adapter(demoData,this)

    }

    override fun onItemClick(item: DemoData) {
        val intent = Intent(requireContext(), ChatActivity::class.java)
        intent.putExtra("img", item.image)
        intent.putExtra("user_name", item.name)
        intent.putExtra("last_time", item.time)
        intent.putExtra("last_msg", item.lastMsg)
        startActivity(intent)
    }


}