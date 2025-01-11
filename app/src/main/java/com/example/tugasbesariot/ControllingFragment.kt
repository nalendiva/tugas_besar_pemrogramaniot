package com.example.tugasbesariot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.authenticationapp2.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ControllingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControllingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var imageView: ImageView
    private lateinit var socketManager: SocketManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_controlling, container, false)

        // Inisialisasi ImageView
        imageView = view.findViewById(R.id.camera_view)

        // Menyiapkan SocketManager untuk menerima data
        socketManager = SocketManager()
        socketManager.setOnCameraReceivedListener { image ->
            // Pastikan base64Image yang diterima adalah string base64 yang valid
            requireActivity().runOnUiThread {
                val bitmapBytes = Base64.decode(image, Base64.DEFAULT)
                if (bitmapBytes != null) {
                    // Menampilkan gambar di ImageView
                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.size))
                } else {
                    // Menangani kesalahan jika base64Image tidak valid
                    println("Invalid image data received")
                }
            }
        }
    return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        socketManager.disconnect() // Menutup koneksi saat fragment dihancurkan
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ControllingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ControllingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}