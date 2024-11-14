package com.example.authenticationapp2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BasicCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_basic_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_basiccalculator)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize views here after they've been created
        val btnTambah =findViewById<Button>(R.id.btn_tambah)
        btnTambah.setOnClickListener {
            tambah() // Call function on button click
        }

        val btnKurang = findViewById<Button>(R.id.btn_kurang)
        btnKurang.setOnClickListener {
            kurang()
        }

        val btnKali = findViewById<Button>(R.id.btn_kali)
        btnKali.setOnClickListener {
            kali()
        }

        val btnBagi = findViewById<Button>(R.id.btn_bagi)
        btnBagi.setOnClickListener {
            bagi()
        }


        val btnClear = findViewById<Button>(R.id.btn_clear)
        btnClear.setOnClickListener{
            clear()
        }

        val btnBack = findViewById<Button>(R.id.btn_back_basic)
        btnBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }


    }


    fun tambah() {
        val angkaPertama = findViewById<EditText>(R.id.et_angka_pertama)
        val angkaKedua = findViewById<EditText>(R.id.et_angka_kedua)
        val hasil = findViewById<TextView>(R.id.tv_hasil)

        // Validasi input kosong
        if (angkaPertama.text.toString().isEmpty()) {
            angkaPertama.error = "Masukkan angka!"
        } else if (angkaKedua.text.toString().isEmpty()) {
            angkaKedua.error = "Masukkan angka!"
        } else {
            val jumlah = angkaPertama.text.toString().toDouble() + angkaKedua.text.toString().toDouble()
            hasil.text = jumlah.toString()
        }
    }

    fun kurang() {
        val angkaPertama = findViewById<EditText>(R.id.et_angka_pertama)
        val angkaKedua = findViewById<EditText>(R.id.et_angka_kedua)
        val hasil = findViewById<TextView>(R.id.tv_hasil)

        // Validasi input kosong
        if (angkaPertama.text.toString().isEmpty()) {
            angkaPertama.error = "Masukkan angka!"
        } else if (angkaKedua.text.toString().isEmpty()) {
            angkaKedua.error = "Masukkan angka!"
        } else {
            val kurang = angkaPertama.text.toString().toDouble() - angkaKedua.text.toString().toDouble()
            hasil.text = kurang.toString()
        }
    }

    fun kali() {
        val angkaPertama = findViewById<EditText>(R.id.et_angka_pertama)
        val angkaKedua = findViewById<EditText>(R.id.et_angka_kedua)
        val hasil = findViewById<TextView>(R.id.tv_hasil)

        if (angkaPertama.text.toString().isEmpty()) {
            angkaPertama.error = "Masukkan angka!"
        } else if (angkaKedua.text.toString().isEmpty()) {
            angkaKedua.error = "Masukkan angka!"
        } else {
            val kali = angkaPertama.text.toString().toDouble() * angkaKedua.text.toString().toDouble()
            hasil.text = kali.toString()
        }
    }

    fun bagi() {
        val angkaPertama = findViewById<EditText>(R.id.et_angka_pertama)
        val angkaKedua = findViewById<EditText>(R.id.et_angka_kedua)
        val hasil = findViewById<TextView>(R.id.tv_hasil)

        if (angkaPertama.text.toString().isEmpty()) {
            angkaPertama.error = "Masukkan angka!"
        } else if (angkaKedua.text.toString().isEmpty()) {
            angkaKedua.error = "Masukkan angka!"
        } else {
            val bagi = angkaPertama.text.toString().toDouble() / angkaKedua.text.toString().toDouble()
            hasil.text = bagi.toString()
        }
    }

//    fun hitungSin() {
//        val inputAngka = findViewById<EditText>(R.id.et_angka_pertama)
//        val hasil = findViewById<TextView>(R.id.tv_hasil)
//
//        if (inputAngka.text.toString().isEmpty()) {
//            inputAngka.error = "Masukkan angka!"
//        } else {
//            val angka = inputAngka.text.toString().toDouble()
//            val sinHasil = kotlin.math.sin(Math.toRadians(angka))
//            hasil.text = "sin($angka) = $sinHasil"
//        }
//    }
//
//    fun hitungCos() {
//        val inputAngka = findViewById<EditText>(R.id.et_angka_pertama)
//        val hasil = findViewById<TextView>(R.id.tv_hasil)
//
//        if (inputAngka.text.toString().isEmpty()) {
//            inputAngka.error = "Masukkan angka!"
//        } else {
//            val angka = inputAngka.text.toString().toDouble()
//            val cosHasil = kotlin.math.cos(Math.toRadians(angka))
//            hasil.text = "cos($angka) = $cosHasil"
//        }
//    }
//
//    fun hitungTan() {
//        val inputAngka = findViewById<EditText>(R.id.et_angka_pertama)
//        val hasil = findViewById<TextView>(R.id.tv_hasil)
//
//        if (inputAngka.text.toString().isEmpty()) {
//            inputAngka.error = "Masukkan angka!"
//        } else {
//            val angka = inputAngka.text.toString().toDouble()
//            val tanHasil = kotlin.math.tan(Math.toRadians(angka))
//            hasil.text = "tan($angka) = $tanHasil"
//        }
//    }

    fun clear() {
        findViewById<EditText>(R.id.et_angka_pertama).text.clear()
        findViewById<EditText>(R.id.et_angka_kedua).text.clear()
        findViewById<TextView>(R.id.tv_hasil).text = "0"
    }


}