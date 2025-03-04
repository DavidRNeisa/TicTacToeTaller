package com.example.tictactoetaller

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoetaller.databinding.ActivityRandomGreetBinding

class RandomGreetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomGreetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomGreetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtiene el idioma seleccionado del Intent y muestra el saludo correspondiente
        val language = intent.getStringExtra("LANGUAGE")
        binding.textView.text = getGreeting(language)
    }

    // Devuelve el saludo apropiado basado en el idioma proporcionado
    private fun getGreeting(language: String?): String {
        return when (language) {
            "English" -> getString(R.string.greeting_english)
            "Spanish" -> getString(R.string.greeting_spanish)
            "French" -> getString(R.string.greeting_french)
            "German" -> getString(R.string.greeting_german)
            "Italian" -> getString(R.string.greeting_italian)
            "Portuguese" -> getString(R.string.greeting_portuguese)
            else -> getString(R.string.greeting_english)
        }
    }
}