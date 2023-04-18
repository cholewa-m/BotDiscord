package com.example

import com.jessecorbett.diskord.util.*
import com.jessecorbett.diskord.api.channel.ChannelClient
import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.events
import com.jessecorbett.diskord.internal.client.RestClient
import kotlinx.coroutines.*

private const val BOT_TOKEN = "your-bot-token"
private const val CHANNEL_ID = "channel-id"

fun main() {
    runBlocking {
        val client = RestClient.default(BOT_TOKEN)
        val channel = ChannelClient(CHANNEL_ID, client)

        launch {
            bot(BOT_TOKEN) {
                events {
                    onMessageCreate { println("\nReceived Message - ${it.author.username}: ${it.content} \nSend message to your channel: " ) }
                }
            }
        }

        println("You can now send and receive messages! ")

        while (true) {
            print("Send message to your channel: ")
            val message: String = readLine() ?: ""
            if (message.isNotBlank())
                channel.sendMessage(message)
        }
    }
}
