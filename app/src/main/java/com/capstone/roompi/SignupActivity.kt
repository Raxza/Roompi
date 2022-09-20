package com.capstone.roompi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.capstone.roompi.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setUpAction()
    }

    private fun setUpAction() {
        binding.btnSignup.setOnClickListener {
            val email = binding.etEmailSign.text.toString()
            val password = binding.etPasswordSign.text.toString()
            when {
                email.isEmpty() -> {
                    binding.etEmailSign.error = getString(R.string.insert_email)
                }
                password.isEmpty() -> {
                    binding.etPasswordSign.error = getString(R.string.insert_password)
                }
                else -> {
                    showProgressBar(true)
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                showProgressBar(false)
                                Log.d(TAG, "createUserWithEmail:success")
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                showProgressBar(false)
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    getString(R.string.aunthentication_failed),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.pbLoading.isVisible = isLoading
    }

    companion object {
        private const val TAG = "SignupActivity"
    }
}