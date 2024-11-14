package com.example.authenticationapp2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menghubungkan fungsi openBasicCalculator dengan tombol atau LinearLayout yang ada di fragment
        val basicCalculatorButton = view.findViewById<View>(R.id.basicCalculatorLayout)
        basicCalculatorButton.setOnClickListener {
            openBasicCalculator()
        }
        val bmiCalculator = view.findViewById<View>(R.id.bmiCalculatorLayout)
        bmiCalculator.setOnClickListener{
            openBMICalculator()
        }
        val converterCalculatorButton = view.findViewById<View>(R.id.tesLayout)
        converterCalculatorButton.setOnClickListener{
            openConverterCalculator()
        }

        val financialCalculator = view.findViewById<View>(R.id.financialCalculatorLayout)
        financialCalculator.setOnClickListener{
            openFinancialCalculator()
        }

        val scientificCalculator = view.findViewById<View>(R.id.scientificCalculatorLayout)
        scientificCalculator.setOnClickListener{
            openScientificCalculator()
        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun openBasicCalculator() {
        Toast.makeText(context, "Buka Kalkulator Dasar", Toast.LENGTH_SHORT).show()

        // Membuka BasicCalculatorActivity menggunakan Intent
        val intent = Intent(context, BasicCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun openBMICalculator(){
        Toast.makeText(context, "Buka Kalkulator BMI", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, BmiCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun openConverterCalculator(){
        Toast.makeText(context,"Buka Converter", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, ConverterCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun openFinancialCalculator(){
        Toast.makeText(context,"Buka Kalkulator Keuangan", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, FinancialCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun openScientificCalculator(){
        Toast.makeText(context,"Buka Kalkulator Ilmiah", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, ScientificCalculatorActivity::class.java)
        startActivity(intent)

    }







}