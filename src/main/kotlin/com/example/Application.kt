package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.jessecorbett.diskord.bot.*
import com.jessecorbett.diskord.util.*
import com.jessecorbett.diskord.api.channel.ChannelClient
import com.jessecorbett.diskord.internal.client.RestClient
import kotlinx.coroutines.coroutineScope

private const val BOT_TOKEN = "MTA5NzkxMTU4MzYzNzc4MjY2MA.GDfIHZ.kO8ZpeTW4aU9tjwUAwLED0FZ_TDFzOsSG_hRfk"  //MTA5NzkxMTU4MzYzNzc4MjY2MA.GDfIHZ.kO8ZpeTW4aU9tjwUAwLED0FZ_TDFzOsSG_hRfk
private const val CHANNEL_ID = "1097901314920431660"    //1097901314920431660


suspend fun main(): Unit = coroutineScope {

    val client = RestClient.default(BOT_TOKEN)
    val channel = ChannelClient(CHANNEL_ID, client)

    println("You can now send messages to your channel! ")

    while(true) {
        print("Enter your message: ")
        val message: String = readLine() ?: ""
        if (message.isNotBlank())
            channel.sendMessage(message)
    }

}
