package com.example.tictactoetaller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoetaller.databinding.ActivityCountriesBinding
import org.json.JSONObject
import paises.Country
import com.blongho.country_data.World

class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesBinding
    private lateinit var countries: MutableList<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        World.init(this)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Cargar la lista de países desde el archivo JSON
        countries = loadCountries()
        // Configurar el adaptador para la lista de países
        //setupListView()
        val adapter = Adapter(this, countries)
        binding.countryListView.adapter = adapter
    }

    private fun setupListView() {
        // Crear un adaptador personalizado para la lista
        val adapter = object : BaseAdapter() {
            override fun getCount() = countries.size
            override fun getItem(position: Int) = countries[position]
            override fun getItemId(position: Int) = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val view = convertView ?: LayoutInflater.from(this@CountriesActivity).inflate(android.R.layout.simple_list_item_1, parent, false)
                val country = getItem(position) as Country
                view.findViewById<TextView>(android.R.id.text1).text = country.nombre_pais
                return view
            }
        }
        // Establecer el adaptador en el ListView
        binding.countryListView.adapter = adapter
        //configurar clicks
        binding.countryListView.setOnItemClickListener { _, _, position, _ ->
            navigateToCountryDetail(countries[position])
        }
    }
    //manda a la otra actividad con los detalles del pais
    fun navigateToCountryDetail(country: Country) {
        val intent = Intent(this, CountryDetailActivity::class.java).apply {
            putExtra("nombre_pais", country.nombre_pais)
            putExtra("capital", country.capital)
            putExtra("nombre_pais_int", country.nombre_pais_int)
            putExtra("sigla", country.sigla)
        }
        startActivity(intent)
    }
    //Carga la lista de países desde un archivo JSON en el folder de assets
    private fun loadCountries(): MutableList<Country> {
        val countriesList = mutableListOf<Country>()
        val jsonString = assets.open("paises.json").bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val paisesJsonArray = json.getJSONArray("paises")

        // Iterar sobre el array de países
        for (i in 0 until paisesJsonArray.length()) {
            val jsonObject = paisesJsonArray.getJSONObject(i)
            val name = jsonObject.getString("nombre_pais")
            val capital = jsonObject.getString("capital")
            val nombrePaisInt = jsonObject.getString("nombre_pais_int")
            val sigla = jsonObject.getString("sigla")
            // Crear un objeto Country y agregarlo a la lista
            val country = Country(name, capital, nombrePaisInt, sigla)
            countriesList.add(country)
        }

        return countriesList
    }


}
