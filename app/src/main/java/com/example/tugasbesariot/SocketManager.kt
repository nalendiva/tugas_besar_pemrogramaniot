package com.example.tugasbesariot

import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URI

class SocketManager {

    private var socket: Socket? = null

    // Fungsi untuk membuka koneksi ke WebSocket
    fun connect() {
//        val uri = URI() // Ganti dengan URL server WebSocket Anda
        // 192.168.7.147

        val uri = URI("ws://192.168.7.147") // Ganti dengan URL server WebSocket Anda
        socket = IO.socket(uri)

        // Menangani koneksi
        socket?.on(Socket.EVENT_CONNECT) {
            println("Connected to WebSocket")
        }

        // Menangani pesan yang diterima
        socket?.on("data") { args ->
            if (args.isNotEmpty()) {
                val data = args[0] as JSONObject

                // Menggunakan optDouble untuk mendapatkan nilai default -1.0
                val power = data.optDouble("power", 0.0)
                val attention = data.optDouble("attention", 0.0)
                val meditation = data.optDouble("meditation", 0.0)
                val lowAlpha = data.optDouble("lowAlpha", 0.0)
                val highAlpha = data.optDouble("highAlpha", 0.0)
                val lowBeta = data.optDouble("lowBeta", 0.0)
                val highBeta = data.optDouble("highBeta", 0.0)
                val lowGamma = data.optDouble("lowGamma", 0.0)
                val midGamma = data.optDouble("midGamma", 0.0)

                onDataReceived(power, "power")
                onDataReceived(attention, "attention")
                onDataReceived(meditation, "meditation")
                onDataReceived(lowAlpha, "lowAlpha")
                onDataReceived(highAlpha, "highAlpha")
                onDataReceived(lowBeta, "lowBeta")
                onDataReceived(highBeta, "highBeta")
                onDataReceived(lowGamma, "lowGamma")
                onDataReceived(midGamma, "midGamma")

                // Anda bisa menambahkan log untuk variabel lainnya jika diperlukan
                android.util.Log.d("EEG-DATA", "Power: $power")
                android.util.Log.d("EEG-DATA", "Low Alpha: $lowAlpha")
                android.util.Log.d("EEG-DATA", "High Alpha: $highAlpha")
                android.util.Log.d("EEG-DATA", "Low Beta: $lowBeta")
                android.util.Log.d("EEG-DATA", "High Beta: $highBeta")
            }
        }


        // Menangani error
        socket?.on(Socket.EVENT_CONNECT_ERROR) {
            println("Error: ${it[0]}")
        }

        socket?.connect()
    }

    // Fungsi untuk mengirim data ke server (jika diperlukan)
    fun sendData(message: String) {
        socket?.emit("sendData", message)
    }

    // Fungsi untuk menerima data dari server dan meneruskan ke listener
    private var onDataReceived: (Double, String) -> Unit = { d: Double, s: String -> }
    fun setOnDataReceivedListener(listener: (Double, String) -> Unit) {
        onDataReceived = listener
    }

    private var onCameraReceived: (String) -> Unit = { s: String -> }
    fun setOnCameraReceivedListener(listener: (String) -> Unit) {
        onCameraReceived = listener
    }

    // Fungsi untuk menutup koneksi
    fun disconnect() {
        socket?.disconnect()
    }
}