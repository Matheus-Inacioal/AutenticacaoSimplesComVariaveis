package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvErro: TextView
    private lateinit var tvCadastro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etSenha = findViewById(R.id.etSenha)
        btnLogin = findViewById(R.id.btnLogin)
        tvErro = findViewById(R.id.tvErro)
        tvCadastro = findViewById(R.id.tvCadastro)

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

        // Configurar o OnClickListener para o TextView de cadastro
        tvCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarCredenciais(nomeUsuario: String, senha: String): Boolean {
        // Verificar as credenciais cadastradas
        return nomeUsuario == CadastroActivity.CredenciaisArmazenadas.nomeUsuario && senha == CadastroActivity.CredenciaisArmazenadas.senha
    }
}


