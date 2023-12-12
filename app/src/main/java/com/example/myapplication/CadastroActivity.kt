package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class CadastroActivity : AppCompatActivity() {

    private lateinit var etNomeUsuario: EditText
    private lateinit var etEmail: EditText
    private lateinit var etSenhaCadastro: EditText
    private lateinit var btnCadastrar: Button

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicialização das views
        etNomeUsuario = findViewById(R.id.etNomeUsuario)
        etEmail = findViewById(R.id.etEmail)
        etSenhaCadastro = findViewById(R.id.etSenhaCadastro)
        btnCadastrar = findViewById(R.id.btnCadastrar)

        // Inicialização do FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Configurar um listener de clique para o botão de cadastro
        btnCadastrar.setOnClickListener {
            val nomeUsuario = etNomeUsuario.text.toString()
            val email = etEmail.text.toString()
            val senha = etSenhaCadastro.text.toString()

            // Criar um usuário no Firebase
            firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Se o cadastro for bem-sucedido, obter o usuário atual
                        val user: FirebaseUser? = firebaseAuth.currentUser

                        // Atualizar o perfil do usuário com o nome
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nomeUsuario)
                            .build()

                        user?.updateProfile(profileUpdates)

                        // Ir para a tela de login após o cadastro
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Se falhar, tratar o erro
                        task.exception?.let { exception ->
                            // Tratar a exceção aqui
                            // Exibir uma mensagem de erro para o usuário
                            Toast.makeText(
                                this@CadastroActivity,
                                "Erro ao cadastrar: ${exception.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }

    object CredenciaisArmazenadas {
        var nomeUsuario: String = ""
        var email: String = ""
        var senha: String = ""
    }

}