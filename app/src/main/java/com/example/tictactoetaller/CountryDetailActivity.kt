package com.example.tictactoetaller

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoetaller.databinding.ActivityCountryDetailBinding
import com.blongho.country_data.World

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtener los datos del país del intent
        val nombrePais = intent.getStringExtra("nombre_pais")
        val capital = intent.getStringExtra("capital")
        val nombrePaisInt = intent.getStringExtra("nombre_pais_int")
        val sigla = intent.getStringExtra("sigla")
        // Mostrar los detalles del país
        binding.countryName.text = nombrePais
        binding.capital.text = capital
        binding.countryIntName.text = nombrePaisInt
        binding.sigla.text = sigla
        World.init(this)
        val imageView: ImageView = findViewById(R.id.countryFlag)
        imageView.setImageResource(World.getFlagOf(sigla))
    }
}
