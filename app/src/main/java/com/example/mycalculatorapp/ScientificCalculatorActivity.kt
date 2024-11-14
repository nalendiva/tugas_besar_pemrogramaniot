package com.example.authenticationapp2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScientificCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scientific_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_scientificcalculator)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<Button>(R.id.btn_back_scientific)
        btnBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        val btnSin = findViewById<Button>(R.id.btn_sin)
        btnSin.setOnClickListener{
            hitungSin()
        }

        val btnCos = findViewById<Button>(R.id.btn_cos)
        btnCos.setOnClickListener{
            hitungCos()
        }

        val btnTan = findViewById<Button>(R.id.btn_tan)
        btnTan.setOnClickListener{
            hitungTan()
        }

        val btnClear = findViewById<Button>(R.id.btn_clear_scientific)
        btnClear.setOnClickListener{
            clear()
        }


    }


    fun hitungSin() {
        val inputAngka = findViewById<EditText>(R.id.et_angka_pertama)
        val hasil = findViewById<TextView>(R.id.tv_hasil)

        if (inputAngka.text.toString().isEmpty()) {
            inputAngka.error = "Masukkan angka!"
        } else {
            val angka = inputAngka.text.toString().toDouble()
            val sinHasil = kotlin.math.sin(Math.toRadians(angka))
            hasil.text = "sin($angka) = $sinHasil"
        }
    }

    fun hitungCos() {
        val inputAngka = findViewById<EditText>(R.id.et_angka_pertama)
        val hasil = findViewById<TextView>(R.id.tv_hasil)

        if (inputAngka.text.toString().isEmpty()) {
            inputAngka.error = "Masukkan angka!"
        } else {
            val angka = inputAngka.text.toString().toDouble()
            val cosHasil = kotlin.math.cos(Math.toRadians(angka))
            hasil.text = "cos($angka) = $cosHasil"
        }
    }

    fun hitungTan() {
        val inputAngka = findViewById<EditText>(R.id.et_angka_pertama)
        val hasil = findViewById<TextView>(R.id.tv_hasil)

        if (inputAngka.text.toString().isEmpty()) {
            inputAngka.error = "Masukkan angka!"
        } else {
            val angka = inputAngka.text.toString().toDouble()
            val tanHasil = kotlin.math.tan(Math.toRadians(angka))
            hasil.text = "tan($angka) = $tanHasil"
        }
    }

    fun clear() {
        findViewById<EditText>(R.id.et_angka_pertama).text.clear()
        findViewById<TextView>(R.id.tv_hasil).text = "0"
    }
}