package com.example.tugasbesariot

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.authenticationapp2.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MonitoringFragment : Fragment() {

    private lateinit var lineChart: LineChart
    private val dataPoints = mutableListOf<Entry>() // Menyimpan data grafik
    private lateinit var socketManager: SocketManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_monitoring, container, false)

        // Inisialisasi LineChart
        lineChart = view.findViewById(R.id.line_chart_container)

        // Membuat dataset untuk grafik
        val dataSet = LineDataSet(dataPoints, "EEG Data")
        dataSet.setDrawValues(false) // Menyembunyikan label nilai di atas titik
        dataSet.color = resources.getColor(R.color.primary_color, null) // Warna garis
        dataSet.setCircleColor(resources.getColor(R.color.primary_color, null)) // Warna titik
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()

        // Menyiapkan SocketManager
        socketManager = SocketManager()
        socketManager.setOnDataReceivedListener { value, valueType ->
            requireActivity().runOnUiThread {
                updateChart(value, valueType)
            }
        }

        // Menghubungkan ke WebSocket
        socketManager.connect()

        return view
    }


    private val powerPoints = mutableListOf<Entry>()
    private val attentionPoints = mutableListOf<Entry>()
    private val meditationPoints = mutableListOf<Entry>()
    private val lowAlphaPoints = mutableListOf<Entry>()
    private val highAlphaPoints = mutableListOf<Entry>()
    private val lowBetaPoints = mutableListOf<Entry>()
    private val highBetaPoints = mutableListOf<Entry>()
    private val lowGammaPoints = mutableListOf<Entry>()
    private val midGammaPoints = mutableListOf<Entry>()


    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateChart(newValue: Double, valueType: String) {
        // Log data yang diterima
        println("Received value: $newValue")

        // Validasi nilai
        if (newValue.isNaN() || newValue.isInfinite()) {
            println("Invalid value received: $newValue")
            return  // Tidak memproses nilai yang tidak valid
        }

        // Menambahkan data baru ke grafik berdasarkan jenis nilai
        when (valueType) {
            "power" -> {
                powerPoints.add(Entry(powerPoints.size.toFloat(), newValue.toFloat()))
            }
            "attention" -> {
                attentionPoints.add(Entry(attentionPoints.size.toFloat(), newValue.toFloat()))
            }
            "meditation" -> {
                meditationPoints.add(Entry(meditationPoints.size.toFloat(), newValue.toFloat()))
            }
            "lowAlpha" -> {
                lowAlphaPoints.add(Entry(lowAlphaPoints.size.toFloat(), newValue.toFloat()))
            }
            "highAlpha" -> {
                highAlphaPoints.add(Entry(highAlphaPoints.size.toFloat(), newValue.toFloat()))
            }
            "lowBeta" -> {
                lowAlphaPoints.add(Entry(lowAlphaPoints.size.toFloat(), newValue.toFloat()))
            }
            "highBeta" -> {
                highBetaPoints.add(Entry(highBetaPoints.size.toFloat(), newValue.toFloat()))
            }
            "lowGamma" -> {
                lowAlphaPoints.add(Entry(lowAlphaPoints.size.toFloat(), newValue.toFloat()))
            }
            "midGamma" -> {
                midGammaPoints.add(Entry(midGammaPoints.size.toFloat(), newValue.toFloat()))
            }
        }

        // Pastikan data tidak kosong
        // Membuat atau memperbarui LineData jika diperlukan

        val powerDataSet = LineDataSet(powerPoints, "Power")
        powerDataSet.color = resources.getColor(R.color.purple_200, null)
        powerDataSet.setCircleColor(resources.getColor(R.color.purple_200, null))

        val attentionDataSet = LineDataSet(attentionPoints, "Attention")
        attentionDataSet.color = resources.getColor(R.color.primary_color, null)
        attentionDataSet.setCircleColor(resources.getColor(R.color.primary_color, null))

        val meditationDataSet = LineDataSet(meditationPoints, "meditation")
        meditationDataSet.color = resources.getColor(R.color.primary_color, null)
        meditationDataSet.setCircleColor(resources.getColor(R.color.primary_color, null))

        val lowAlphaDataSet = LineDataSet(lowAlphaPoints, "Low Alpha")
        lowAlphaDataSet.color = resources.getColor(R.color.purple_200, null)
        lowAlphaDataSet.setCircleColor(resources.getColor(R.color.purple_200, null))

        val highAlphaDataSet = LineDataSet(highAlphaPoints, "High Alpha")
        highAlphaDataSet.color = resources.getColor(R.color.teal_200, null)
        highAlphaDataSet.setCircleColor(resources.getColor(R.color.teal_700, null))

        val lowBetaDataSet = LineDataSet(lowBetaPoints, "Low Beta")
        lowBetaDataSet.color = resources.getColor(R.color.purple_200, null)
        lowBetaDataSet.setCircleColor(resources.getColor(R.color.purple_200, null))

        val highBetaDataSet = LineDataSet(highBetaPoints, "High Beta")
        highBetaDataSet.color = resources.getColor(R.color.teal_200, null)
        highBetaDataSet.setCircleColor(resources.getColor(R.color.teal_700, null))

        val lowGammaDataSet = LineDataSet(lowGammaPoints, "Low Gamma")
        lowGammaDataSet.color = resources.getColor(R.color.purple_200, null)
        lowGammaDataSet.setCircleColor(resources.getColor(R.color.purple_200, null))

        val midGammaDataSet = LineDataSet(midGammaPoints, "Middle Gamma")
        midGammaDataSet.color = resources.getColor(R.color.teal_200, null)
        midGammaDataSet.setCircleColor(resources.getColor(R.color.teal_700, null))
        
        // Membuat LineData dengan beberapa dataset
        val lineData = LineData(powerDataSet, attentionDataSet, meditationDataSet, lowAlphaDataSet, highAlphaDataSet, lowBetaDataSet, highBetaDataSet, lowGammaDataSet, midGammaDataSet)

        // Update LineData ke chart
        lineChart.data = lineData
        lineChart.data.notifyDataChanged()  // Memberitahu grafik bahwa data telah berubah
        lineChart.notifyDataSetChanged()  // Memperbarui tampilan grafik
        lineChart.invalidate()  //
    }


    override fun onDestroyView() {
        super.onDestroyView()
        socketManager.disconnect() // Menutup koneksi saat fragment dihancurkan
    }
}
