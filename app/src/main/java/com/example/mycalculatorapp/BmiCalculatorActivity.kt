package com.example.authenticationapp2

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BmiCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_bmicalculator)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<Button>(R.id.btn_back_bmi)
        btnBack.setOnClickListener {
            // Kembali ke halaman sebelumnya
            onBackPressedDispatcher.onBackPressed()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        val etWeight = findViewById<EditText>(R.id.et_weight)
        val etHeight = findViewById<EditText>(R.id.et_height)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate_bmi)
        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvCategory = findViewById<TextView>(R.id.tv_category)

        btnCalculate.setOnClickListener {
            val weight = etWeight.text.toString().toDoubleOrNull()
            val heightInput = etHeight.text.toString().toDoubleOrNull()

            // Validasi input
            if (weight == null || heightInput == null || weight <= 0 || heightInput <= 0) {
                Toast.makeText(this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Konversi tinggi badan ke meter jika dalam sentimeter
            val height = if (heightInput > 10) heightInput / 100 else heightInput

            // Menghitung BMI
            val bmi = weight / (height * height)

            // Menampilkan hasil
            tvResult.text = "Hasil BMI: %.2f".format(bmi)

            // Menentukan kategori BMI
            val category = when {
                bmi < 18.5 -> "Kurus"
                bmi in 18.5..24.9 -> "Normal"
                bmi in 25.0..29.9 -> "Berat badan lebih"
                else -> "Obesitas"
            }

            tvCategory.text = "Kategori: $category"
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed() // Kembali ke halaman sebelumnya
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }









}