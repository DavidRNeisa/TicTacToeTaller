package com.example.tictactoetaller

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoetaller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupButtons()
    }
    // se crea un arreglo y se cargan esos valores en el spinner
    private fun setupSpinner() {
        val languages = arrayOf("English", "Spanish", "French", "German", "Italian", "Portuguese")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        binding.spinnerLanguages.adapter = adapter
    }

    private fun setupButtons() {
        // manda a la actividad de triqui
        binding.btnTicTacToe.setOnClickListener {
            startActivity(Intent(baseContext, TicTacToeActivity::class.java))
        }
        // manda a al saludo incluyendo el lenguaje seleccionado
        binding.btnRandomGreet.setOnClickListener {
            val selectedLanguage = binding.spinnerLanguages.selectedItem.toString()
            val intent = Intent(baseContext, RandomGreetActivity::class.java)
            intent.putExtra("LANGUAGE", selectedLanguage)
            startActivity(intent)
        }
        // manda a la actividad de la lista de paises
        binding.btnCountries.setOnClickListener {
            startActivity(Intent(baseContext, CountriesActivity::class.java))
        }
    }

}