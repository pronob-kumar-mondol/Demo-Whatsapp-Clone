package com.example.whatsappclone.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.util.TypedValueCompat.dpToPx
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.Adapter.Chat_Details_Adapter
import com.example.whatsappclone.Data.Message
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityChatBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatActivity : AppCompatActivity() ,Chat_Details_Adapter.OnItemClickListener{

    private lateinit var binding: ActivityChatBinding

    private val messages = mutableListOf<Message>()
    private lateinit var adapter: Chat_Details_Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initComponent()

        setRecyclerView()

        goToUserInfo()

        goback()


    }

    private fun goback() {

    }

    private fun goToUserInfo() {
        binding.userInfoClickable.setOnClickListener {
            intent= Intent(this,User_Profile_Activity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = Chat_Details_Adapter(messages, this) // Pass the listener
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun initComponent() {
        binding.btnSend.setOnClickListener {
            sendChat()
        }
    }

    private fun sendChat() {

        val msgText=binding.textContent.text.toString().trim()

        if (msgText.isNotEmpty()){
            //Create a new msg object
            val msg=Message(content = msgText, isSent = true, timestamp = getCurrentTime())

            //Add the msg to the list
            messages.add(msg)

            // Notify the adapter that a new message has been added
            adapter.notifyItemInserted(messages.size - 1)

            // Scroll to the bottom of the RecyclerView
            binding.recyclerView.scrollToPosition(messages.size - 1)

            // Clear the EditText
            binding.textContent.text.clear()

            // Hide the keyboard
            hideKeyboard()

            // Simulate receiving a reply
            simulateReply(msg.toString())
        }

    }

    // Function to hide the keyboard
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.textContent.windowToken, 0)
    }

    private fun simulateReply(sentMessage: String) {
        // Delay for a moment to simulate receiving a reply
        Handler(Looper.getMainLooper()).postDelayed({
            val replyMessage = Message(content = "Reply: $sentMessage", isSent = false, timestamp = getCurrentTime())
            messages.add(replyMessage)
            adapter.notifyItemInserted(messages.size - 1)
            binding.recyclerView.scrollToPosition(messages.size - 1)
        }, 1000) // Delay of 1 second for simulation
    }



    // Function to get the current time as a String
    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun initToolbar() {
        //Set up the toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.title=""
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED)
    }

    //Inflate the menu layout
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat_toolbar_menu,menu)

        //Change the color of the icons in the menu
        menu?.findItem(R.id.action_video_call)?.icon?.setTint(ContextCompat.getColor(this, R.color.white))
        menu?.findItem(R.id.action_call)?.icon?.setTint(ContextCompat.getColor(this, R.color.white))
        menu?.findItem(R.id.action_more)?.icon?.setTint(ContextCompat.getColor(this, R.color.white))


        return true
    }


    /**Here Will Implement Long click to delete, copy, reply and share message **/
    override fun onItemClick(message: Message) {
        // Handle message click
        Toast.makeText(this, "Clicked on: ${message.content}", Toast.LENGTH_SHORT).show()
    }




    //Handle menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_video_call->{
                //Handle video call action
                true
            }

            R.id.action_call->{
                //Handle call action
                true
            }

            R.id.action_more->{
                //Handle more action
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}