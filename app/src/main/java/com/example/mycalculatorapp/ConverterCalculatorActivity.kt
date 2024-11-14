package com.example.authenticationapp2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConverterCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_converter_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_convertercalculatorutama)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<Button>(R.id.btn_back_converter)
        btnBack.setOnClickListener {
            // Kembali ke halaman sebelumnya
            onBackPressedDispatcher.onBackPressed()
        }
        Toast.makeText(applicationContext,"tes", Toast.LENGTH_SHORT).show()



        val spinnerConversionType = findViewById<Spinner>(R.id.spinner_conversion_type)
        val etInputValue = findViewById<EditText>(R.id.et_input_value)
        val spinnerFromUnit = findViewById<Spinner>(R.id.spinner_from_unit)
        val spinnerToUnit = findViewById<Spinner>(R.id.spinner_to_unit)
        val btnConvert = findViewById<Button>(R.id.btn_convert)
        val tvResult = findViewById<TextView>(R.id.tv_result)


        // Daftar opsi untuk konversi
        val conversionTypes = arrayOf("Panjang", "Berat", "Suhu")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionTypes)
        Toast.makeText(applicationContext,"gue berhasil1", Toast.LENGTH_SHORT).show()

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        Toast.makeText(applicationContext,"gue berhasil2", Toast.LENGTH_SHORT).show()

        spinnerConversionType.adapter = arrayAdapter
        Toast.makeText(applicationContext,"gue berhasil", Toast.LENGTH_SHORT).show()

        // Ketika jenis konversi dipilih
        spinnerConversionType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Menyesuaikan pilihan satuan berdasarkan jenis konversi yang dipilih
                when (position) {
                    0 -> {
                        // Konversi Panjang
                        val lengthUnits = arrayOf("Meter", "Kilometer", "Inci", "Kaki")
                        val lengthAdapter = ArrayAdapter(this@ConverterCalculatorActivity, android.R.layout.simple_spinner_item, lengthUnits)
                        spinnerFromUnit.adapter = lengthAdapter
                        spinnerToUnit.adapter = lengthAdapter
                    }
                    1 -> {
                        // Konversi Berat
                        val weightUnits = arrayOf("Kilogram", "Gram", "Pound")
                        val weightAdapter = ArrayAdapter(this@ConverterCalculatorActivity, android.R.layout.simple_spinner_item, weightUnits)
                        spinnerFromUnit.adapter = weightAdapter
                        spinnerToUnit.adapter = weightAdapter
                    }
                    2 -> {
                        // Konversi Suhu
                        val temperatureUnits = arrayOf("Celsius", "Fahrenheit", "Kelvin")
                        val temperatureAdapter = ArrayAdapter(this@ConverterCalculatorActivity, android.R.layout.simple_spinner_item, temperatureUnits)
                        spinnerFromUnit.adapter = temperatureAdapter
                        spinnerToUnit.adapter = temperatureAdapter
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {}
        }

        // Ketika tombol Konversi diklik
        btnConvert.setOnClickListener {
            val inputValue = etInputValue.text.toString().toDoubleOrNull()
            if (inputValue == null || inputValue <= 0) {
                Toast.makeText(this, "Masukkan nilai yang valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Mendapatkan jenis konversi yang dipilih
            val conversionType = spinnerConversionType.selectedItem.toString()

            // Mendapatkan satuan asal dan satuan tujuan
            val fromUnit = spinnerFromUnit.selectedItem.toString()
            val toUnit = spinnerToUnit.selectedItem.toString()

            // Melakukan konversi berdasarkan jenis konversi yang dipilih
            val result = when (conversionType) {
                "Panjang" -> convertLength(fromUnit, toUnit, inputValue)
                "Berat" -> convertWeight(fromUnit, toUnit, inputValue)
                "Suhu" -> convertTemperature(fromUnit, toUnit, inputValue)
                else -> 0.0
            }

            tvResult.text = "Hasil: %.2f".format(result)
        }
    }

    // Konversi panjang
    private fun convertLength(fromUnit: String, toUnit: String, value: Double): Double {
        return when (fromUnit) {
            "Meter" -> when (toUnit) {
                "Kilometer" -> value / 1000
                "Inci" -> value * 39.3701
                "Kaki" -> value * 3.28084
                else -> value
            }
            "Kilometer" -> when (toUnit) {
                "Meter" -> value * 1000
                "Inci" -> value * 39370.1
                "Kaki" -> value * 3280.84
                else -> value
            }
            "Inci" -> when (toUnit) {
                "Meter" -> value / 39.3701
                "Kilometer" -> value / 39370.1
                "Kaki" -> value / 12
                else -> value
            }
            "Kaki" -> when (toUnit) {
                "Meter" -> value / 3.28084
                "Kilometer" -> value / 3280.84
                "Inci" -> value * 12
                else -> value
            }
            else -> value
        }
    }

    // Konversi berat
    private fun convertWeight(fromUnit: String, toUnit: String, value: Double): Double {
        return when (fromUnit) {
            "Kilogram" -> when (toUnit) {
                "Gram" -> value * 1000
                "Pound" -> value * 2.20462
                else -> value
            }
            "Gram" -> when (toUnit) {
                "Kilogram" -> value / 1000
                "Pound" -> value / 453.592
                else -> value
            }
            "Pound" -> when (toUnit) {
                "Kilogram" -> value / 2.20462
                "Gram" -> value * 453.592
                else -> value
            }
            else -> value
        }
    }

    // Konversi suhu
    private fun convertTemperature(fromUnit: String, toUnit: String, value: Double): Double {
        return when (fromUnit) {
            "Celsius" -> when (toUnit) {
                "Fahrenheit" -> (value * 9/5) + 32
                "Kelvin" -> value + 273.15
                else -> value
            }
            "Fahrenheit" -> when (toUnit) {
                "Celsius" -> (value - 32) * 5/9
                "Kelvin" -> (value - 32) * 5/9 + 273.15
                else -> value
            }
            "Kelvin" -> when (toUnit) {
                "Celsius" -> value - 273.15
                "Fahrenheit" -> (value - 273.15) * 9/5 + 32
                else -> value
            }
            else -> value
        }
    }
}
