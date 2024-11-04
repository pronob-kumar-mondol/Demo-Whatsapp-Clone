package com.example.whatsappclone.Data

data class Message(
    val content: String, // The message text
    val isSent: Boolean, // True if the message is sent by the user, false if received
    val timestamp: String // The time when the message was sent/received
)

