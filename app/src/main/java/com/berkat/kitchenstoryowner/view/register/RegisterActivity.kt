package com.berkat.kitchenstoryowner.view.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.berkat.kitchenstoryowner.R
import com.berkat.kitchenstoryowner.databinding.ActivityRegisterBinding
import com.berkat.kitchenstoryowner.utils.Preferences
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    val viewModel: RegisterViewModel by viewModels()
    lateinit var binding: ActivityRegisterBinding
    var tokenNotif: RequestBody? = null
    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = Preferences(this)

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            val token = it.result
            tokenNotif = token!!.toRequestBody("text/plain".toMediaTypeOrNull())

        }

        binding.btnRegister.setOnClickListener {

            if (validateInput()) {
                viewModel.register(
                    binding.etFullname.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etBrandName.text.toString()
                )
            } else {
                Toast.makeText(this, "Register unsuccessful", Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.registerUser.collect { event ->
                when (event) {

                    is RegisterViewModel.RegisterEvent.Success -> {
                        val tokenJwt = event.kitchenResponse.data!!.tokenJwt.toString()

                        Toast.makeText(
                            this@RegisterActivity,
                            event.kitchenResponse.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is RegisterViewModel.RegisterEvent.Failure -> {
                        Toast.makeText(this@RegisterActivity, event.errorText, Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> Unit
                }
            }
        }

    }

    fun toRequestBody(textInputEditText: TextInputEditText): RequestBody {

        return textInputEditText.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())

    }

    fun validateInput(): Boolean {
        return when {
            binding.etEmail.text!!.isEmpty() -> {
                binding.etEmail.error = getString(R.string.edittext_empty_error)
                false
            }
            binding.etPassword.text!!.isEmpty() -> {
                binding.etPassword.error = getString(R.string.edittext_empty_error)
                false
            }
            binding.etConfirmPassword.text!!.isEmpty() -> {
                binding.etConfirmPassword.error = getString(R.string.edittext_empty_error)
                false
            }
            binding.etFullname.text!!.isEmpty() -> {
                binding.etFullname.error = getString(R.string.edittext_empty_error)
                false
            }
            else -> {
                binding.etPassword.text.toString() == binding.etConfirmPassword.text.toString()
            }
        }
    }

}

