package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvErro: TextView
    private lateinit var tvCadastro: TextView

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização das views
        etUsuario = findViewById(R.id.etUsuario)
        etSenha = findViewById(R.id.etSenha)
        btnLogin = findViewById(R.id.btnLogin)
        tvErro = findViewById(R.id.tvErro)
        tvCadastro = findViewById(R.id.tvCadastro)

        // Inicialização do FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Configurar um listener de clique para o botão de login
        btnLogin.setOnClickListener {
            val nomeUsuario = etUsuario.text.toString()
            val senha = etSenha.text.toString()

            // Autenticar usando o Firebase
            firebaseAuth.signInWithEmailAndPassword(CadastroActivity.CredenciaisArmazenadas.email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Se a autenticação for bem-sucedida, ir para a tela de boas-vindas
                        val intent = Intent(this, WelcomeActivity::class.java)
                        intent.putExtra("nomeUsuario", nomeUsuario)
                        startActivity(intent)
                        finish()
                    } else {
                        // Se falhar, tratar o erro
                        task.exception?.let { exception ->
                            // Tratar a exceção aqui
                            // Exemplo: exibir uma mensagem de erro para o usuário
                            tvErro.text = "Credenciais inválidas"
                            Toast.makeText(
                                this@LoginActivity,
                                "Erro ao fazer login: ${exception.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }

        // Configurar o OnClickListener para o TextView de cadastro
        tvCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}


