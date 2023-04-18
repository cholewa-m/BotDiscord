package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    println("This is a simple Dicord Message Sending Client.")
    print("Enter id of your channel: ")
//    val channel_id_private = 1097901314920431660
    val channel_id = readLine()
    println("You can send messages to your channel with id=$channel_id. Type \"--q\" to exit:")
    while(true) {
        val message = readLine() ?: ""
        if (message.equals("--q", true)) return
        print(message)
    }
}

fun Application.module() {
    configureRouting()
}
