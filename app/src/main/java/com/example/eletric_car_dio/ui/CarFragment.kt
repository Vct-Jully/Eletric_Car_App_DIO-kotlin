package com.example.eletric_car_dio.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletric_car_dio.R
import com.example.eletric_car_dio.domain.Carro
import com.example.eletric_car_dio.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {

    lateinit var fabCalcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView
    lateinit var progress: ProgressBar

    var carrosArray: ArrayList<Carro> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupListeners()
        callService()
    }

    fun setupView(view: View) {
        view.apply {
            fabCalcular = findViewById(R.id.fab_btnCalcular)
            listaCarros = findViewById(R.id.lv_listacarros)
            progress = findViewById(R.id.pb_loader)
        }
    }

    fun setupList() {
        val adapter = CarAdapter(carrosArray)
        listaCarros.visibility = View.VISIBLE
        listaCarros.adapter = adapter
    }

    fun setupListeners() {
        fabCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }

    }

    fun callService() {
        val urlBase = "https://igorbag.github.io/cars-api/cars.json"
        MyTask().execute(urlBase)
        progress.visibility = View.VISIBLE

    }


    inner class MyTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "iniciando...")
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty(
                    "Accept",
                    "aplication/json"
                )

                val responseCode = urlConnection.responseCode

                if(responseCode == HttpURLConnection.HTTP_OK) {
                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)
                }else {
                    Log.e("Erro", "Erro, serviço indisponível no momento...")
                }

            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao realizar processamento...")
            } finally {
                urlConnection?.disconnect()
            }

            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {

            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID ->", id)

                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("Preço ->", preco)

                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("Bateria ->", bateria)

                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("Recarga ->", recarga)

                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("Potência ->", potencia)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("urlPhoto ->", urlPhoto)

                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        potencia = potencia,
                        bateria = bateria,
                        recarga = recarga,
                        urlPhoto = urlPhoto
                    )
                    carrosArray.add(model)
                }
                progress.visibility = View.GONE
                setupList()

            } catch (ex: Exception) {
                Log.e("Erro ->", ex.message.toString())
            }

        }

    }

}