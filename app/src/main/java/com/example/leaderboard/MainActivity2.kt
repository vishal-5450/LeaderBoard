package com.example.leaderboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.leaderboard.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signinBtn.setOnClickListener {
            val email = binding.evsigninEmail.text.toString()
            val password = binding.evsigninPass.text.toString()
            val progress = binding.progressBar

            progress.visibility = View.VISIBLE
            if(email.isNotEmpty() && password.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        progress.visibility = View.GONE
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        progress.visibility = View.GONE
                        Toast.makeText(this,"Wrong Email/Password",Toast.LENGTH_SHORT).show()
                    }
                    }
            }else{
                progress.visibility = View.GONE
                Toast.makeText(this,"Enter your Email/Password",Toast.LENGTH_SHORT).show()
            }
        }
    }
}