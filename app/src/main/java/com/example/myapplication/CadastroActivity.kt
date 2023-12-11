package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity



class CadastroActivity : AppCompatActivity() {

    private lateinit var etNomeUsuario: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenhaCadastro: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        etNomeUsuario = findViewById(R.id.etNomeUsuario)
        etEmail = findViewById(R.id.etEmail)
        etSenhaCadastro = findViewById(R.id.etSenhaCadastro)
        btnCadastrar = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val nomeUsuario = etNomeUsuario.text.toString()
            val email = etEmail.text.toString()
            val senha = etSenhaCadastro.text.toString()

            // Armazenar as credenciais no backend (variáveis estáticas para este exemplo).
            CredenciaisArmazenadas.nomeUsuario = nomeUsuario
            CredenciaisArmazenadas.email = email
            CredenciaisArmazenadas.senha = senha

            // Ir para a tela de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    object CredenciaisArmazenadas {
        var nomeUsuario: String = ""
        var email: String = ""
        var senha: String = ""
    }
}
