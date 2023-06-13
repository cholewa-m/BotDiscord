package com.example

import com.jessecorbett.diskord.util.*
import com.jessecorbett.diskord.api.channel.ChannelClient
import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.events
import com.jessecorbett.diskord.internal.client.RestClient
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

private const val BOT_TOKEN = ""
private const val CHANNEL_ID = ""

private const val API_GPT_KEY = ""
private const val API_GPT_URL = ""

fun main() {
    runBlocking {
        val client = RestClient.default(BOT_TOKEN)
        val channel = ChannelClient(CHANNEL_ID, client)

        val httpClient = OkHttpClient()

        launch {
            bot(BOT_TOKEN) {
                events {
                    onMessageCreate {
                        it.author.isBot?.let {
                            return@onMessageCreate
                        }
                        val userMessage = it.content
                        println("\nReceived Message - ${it.author.username}: $userMessage")

                        val jsonTemplate = """
{
  "model": "gpt-3.5-turbo",
  "messages": [
      {
          "role": "user",
          "content": "userMessage"
      }
  ]
}
""".trimIndent()

                        val json = jsonTemplate.replace("userMessage", userMessage)


                        val request = Request.Builder()
                            .url(API_GPT_URL)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer $API_GPT_KEY")
                            .post(json.toRequestBody("application/json".toMediaTypeOrNull()))
                            .build()


                        val response = httpClient.newCall(request).execute()
                        var gptResponse = response.body?.string()

                        gptResponse = gptResponse?.substringAfter("\"content\": \"").orEmpty()
                        gptResponse = gptResponse.substringBefore("\"")

                        channel.sendMessage(gptResponse)
                    }
                }
            }
        }
    }

}
