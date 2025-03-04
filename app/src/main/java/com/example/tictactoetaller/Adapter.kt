package com.example.tictactoetaller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blongho.country_data.World
import paises.Country

class Adapter(private val mcontex: Context, private val mlist: List<Country>) : ArrayAdapter<Country>(mcontex,0,mlist) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(mcontex).inflate(R.layout.activity_adapter, parent, false)

        val pais = mlist[position]

        val nombre = view.findViewById<TextView>(R.id.nombre)
        val sigla = view.findViewById<TextView>(R.id.siglas)
        val imageView = view.findViewById<ImageView>(R.id.imageView2)

        nombre.text = pais.nombre_pais
        sigla.text = pais.sigla
        imageView.setImageResource(World.getFlagOf(pais.sigla))
        view.setOnClickListener {
            if (mcontex is CountriesActivity) {
                mcontex.navigateToCountryDetail(pais)
            }
        }
        return view
    }
}