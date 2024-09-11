package org.krcoding

import org.java_websocket.server.WebSocketServer
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import java.net.InetSocketAddress
import java.util.concurrent.ConcurrentHashMap

class ChatSocketServer(address: InetSocketAddress) : WebSocketServer(address) {

    // HashMap to store connections with WebSocket instance as the key
    private val connections: ConcurrentHashMap<WebSocket, Unit> = ConcurrentHashMap()

    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        println("New connection: ${conn.remoteSocketAddress}")
        // Add the connection to the HashMap
        connections[conn] = Unit
        conn.send("Welcome to the WebSocket server!")
    }

    override fun onMessage(conn: WebSocket, message: String) {
        println("Message from ${conn.remoteSocketAddress}: $message")

        // Iterate through all connections and send the message to all clients except the sender
        for (client in connections.keys) {
            if (client != conn) { // Ensure the sender doesn't receive their own message
                try {
                    client.send(message)
                } catch (e: Exception) {
                    println("Failed to send message to ${client.remoteSocketAddress}: ${e.message}")
                }
            }
        }
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {
        println("Connection closed: ${conn.remoteSocketAddress}")
        // Remove the connection from the HashMap
        connections.remove(conn)
    }

    override fun onError(conn: WebSocket?, ex: Exception) {
        println("Error occurred: ${ex.message}")
    }

    override fun onStart() {
        println("Starting Server")
    }
}
