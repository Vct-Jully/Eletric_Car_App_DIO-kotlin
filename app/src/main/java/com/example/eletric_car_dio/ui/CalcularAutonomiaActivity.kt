package com.example.eletric_car_dio.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletric_car_dio.R
import java.net.HttpURLConnection
import java.net.URL


class CalcularAutonomiaActivity : AppCompatActivity() {
    lateinit var preco: EditText
    lateinit var btnCalcular: Button
    lateinit var kmpercorrido: EditText
    lateinit var resultado: TextView
    lateinit var btnClose: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LifeCycle", "Create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupView()
        setupListeners()

    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "Resume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "Start")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "Stop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "Pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "Destroy")
    }

    fun setupView() {
        preco = findViewById(R.id.etPrecokWh)
        kmpercorrido = findViewById(R.id.etKmPercorrido)
        btnCalcular = findViewById(R.id.btnCalcular)
        resultado = findViewById(R.id.tv_resultado)
        btnClose = findViewById(R.id.img_close)
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }
        btnClose.setOnClickListener {
            finish()
        }
    }


    fun calcular() {
        val textoPreco = preco.text.toString().toFloat()
        val textokm = kmpercorrido.text.toString().toFloat()
        val result = textoPreco / textokm

        resultado.text = result.toString()
    }

}