package org.krcoding

import java.net.InetSocketAddress

fun main() {

    val address = InetSocketAddress("localhost", 8082)
    val server = ChatSocketServer(address)

    server.start()
    println("WebSocket server started on ws://localhost:")
}
