package com.example.authenticationapp2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinancialCalculatorActivity : AppCompatActivity() {

    private lateinit var etPrincipalAmount: EditText
    private lateinit var etInterestRate: EditText
    private lateinit var etDuration: EditText
    private lateinit var btnCalculateLoanInterest: Button
    private lateinit var btnCalculateSavings: Button
    private lateinit var btnCalculateEmi: Button
    private lateinit var tvLoanResult: TextView
    private lateinit var tvSavingsResult: TextView
    private lateinit var tvEmiResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_financial_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_financialcalculator)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<Button>(R.id.btn_back_financial)
        btnBack.setOnClickListener {
            // Kembali ke halaman sebelumnya
            onBackPressedDispatcher.onBackPressed()
        }

        // Menghubungkan View
        etPrincipalAmount = findViewById(R.id.et_principal_amount)
        etInterestRate = findViewById(R.id.et_interest_rate)
        etDuration = findViewById(R.id.et_duration)
        btnCalculateLoanInterest = findViewById(R.id.btn_calculate_loan_interest)
        btnCalculateSavings = findViewById(R.id.btn_calculate_savings)
        btnCalculateEmi = findViewById(R.id.btn_calculate_emi)
        tvLoanResult = findViewById(R.id.tv_loan_result)
        tvSavingsResult = findViewById(R.id.tv_savings_result)
        tvEmiResult = findViewById(R.id.tv_emi_result)

        // Hitung Bunga Pinjaman
        btnCalculateLoanInterest.setOnClickListener {
            val principal = etPrincipalAmount.text.toString().toDoubleOrNull()
            val interestRate = etInterestRate.text.toString().toDoubleOrNull()
            val duration = etDuration.text.toString().toIntOrNull()

            if (principal != null && interestRate != null && duration != null) {
                // Rumus Bunga Tetap
                val totalInterest = (principal * interestRate * duration) / 100
                tvLoanResult.text = "Total Bunga: %.2f".format(totalInterest)
            } else {
                Toast.makeText(this, "Masukkan nilai yang valid", Toast.LENGTH_SHORT).show()
            }
        }

        // Simulasi Tabungan atau Investasi
        btnCalculateSavings.setOnClickListener {
            val principal = etPrincipalAmount.text.toString().toDoubleOrNull()
            val interestRate = etInterestRate.text.toString().toDoubleOrNull()
            val duration = etDuration.text.toString().toIntOrNull()

            if (principal != null && interestRate != null && duration != null) {
                // Simulasi dengan Bunga Sederhana
                val totalAmount = principal * (1 + (interestRate / 100) * duration)
                tvSavingsResult.text = "Total Setelah Simulasi: %.2f".format(totalAmount)
            } else {
                Toast.makeText(this, "Masukkan nilai yang valid", Toast.LENGTH_SHORT).show()
            }
        }


        // Hitung Angsuran EMI
        btnCalculateEmi.setOnClickListener {
            val principal = etPrincipalAmount.text.toString().toDoubleOrNull()
            val interestRate = etInterestRate.text.toString().toDoubleOrNull()
            val duration = etDuration.text.toString().toIntOrNull()

            if (principal != null && interestRate != null && duration != null) {
                // Rumus EMI (Equated Monthly Installment)
                val monthlyInterestRate = interestRate / 12 / 100
                val emi = (principal * monthlyInterestRate) /
                        (1 - Math.pow(1 + monthlyInterestRate, -duration.toDouble()))
                tvEmiResult.text = "Angsuran Bulanan: %.2f".format(emi)
            } else {
                Toast.makeText(this, "Masukkan nilai yang valid", Toast.LENGTH_SHORT).show()
            }
        }



    }
}