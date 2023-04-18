package com.example

import com.jessecorbett.diskord.util.*
import com.jessecorbett.diskord.api.channel.ChannelClient
import com.jessecorbett.diskord.internal.client.RestClient
import kotlinx.coroutines.coroutineScope


private const val BOT_TOKEN = "your-bot-token"
private const val CHANNEL_ID = "your-channel-id"


suspend fun main(): Unit = coroutineScope {

    val client = RestClient.default(BOT_TOKEN)
    val channel = ChannelClient(CHANNEL_ID, client)

    println("You can now send messages to your channel using your bot! ")

    while(true) {
        print("Enter your message: ")
        val message: String = readLine() ?: ""
        if (message.isNotBlank())
            channel.sendMessage(message)
    }

}
