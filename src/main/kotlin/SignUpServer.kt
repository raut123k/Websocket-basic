package org.krcoding

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress


class SignUpServer(address: InetSocketAddress) : WebSocketServer(address) {

    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        println("New connection: ${conn.remoteSocketAddress}")
        conn.send("Welcome to the WebSocket server!")
    }

    override fun onMessage(conn: WebSocket, message: String) {
        println("Message from ${conn.remoteSocketAddress}: $message")
        conn.send("You sent: $message")
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {
        println("Connection closed: ${conn.remoteSocketAddress}")
    }

    override fun onError(conn: WebSocket?, ex: Exception) {
        println("Error occurred: ${ex.message}")
    }

    override fun onStart() {
        println("starting Server")
    }
}