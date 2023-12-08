package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var tvMensagemBoasVindas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        tvMensagemBoasVindas = findViewById(R.id.tvMensagemBoasVindas)

        val nomeUsuario = intent.getStringExtra("nomeUsuario")
        tvMensagemBoasVindas.text = "Bem-vindo, $nomeUsuario!"
    }
}
