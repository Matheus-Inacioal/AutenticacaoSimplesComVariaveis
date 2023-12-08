package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvErro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etSenha = findViewById(R.id.etSenha)
        btnLogin = findViewById(R.id.btnLogin)
        tvErro = findViewById(R.id.tvErro)

        btnLogin.setOnClickListener {
            val nomeUsuario = etUsuario.text.toString()
            val senha = etSenha.text.toString()

            if (validarCredenciais(nomeUsuario, senha)) {
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("nomeUsuario", nomeUsuario)
                startActivity(intent)
            } else {
                tvErro.text = "Credenciais inválidas"
            }
        }
    }

    private fun validarCredenciais(nomeUsuario: String, senha: String): Boolean {
        val usuarioCorreto = "exemplo"
        val senhaCorreta = "senha123"
        return nomeUsuario == usuarioCorreto && senha == senhaCorreta
    }
}
