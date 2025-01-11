package com.example.authenticationapp2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.authenticationapp2.databinding.ActivityHomeBinding
import com.example.tugasbesariot.ControllingFragment
import com.example.tugasbesariot.MonitoringFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ControllingFragment())

        // Mendapatkan referensi ke TextView yang menampilkan tanggal
        val tvDate = findViewById<TextView>(R.id.tv_date)

        // Mendapatkan tanggal hari ini
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))


        // Menampilkan tanggal yang diformat ke TextView
        tvDate.text = dateFormat.format(currentDate)


        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            // Logika logout, misalnya:
            Toast.makeText(this, "Logout berhasil", Toast.LENGTH_SHORT).show()

            // Contoh: Kembali ke halaman login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Mengakhiri aktivitas saat ini
        }




//        binding.btnLogout.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//        }
//        enableEdgeToEdge()
//        //setContentView(R.layout.activity_home)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.buttonNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_botnav -> replaceFragment(ControllingFragment())
                R.id.profile_botnav->replaceFragment(MonitoringFragment())
                R.id.setting_botnav->replaceFragment(SettingFragment())
                else ->{
                }
            }
            true
        }
    }
//    private fun sendMessage (){
//        val txt_msg = findViewById<EditText>(R.id.edt_msg)
//        val message = txt_msg.text.toString()
//        val intent = Intent(this, DisplayMessageActivity::class.java)
//        intent.apply {
//            putExtra("theMessage", message)
//        }
//        startActivity(intent)
//    }
    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }






}