package com.berkat.kitchenstoryowner.view.informasirekening

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.berkat.kitchenstoryowner.R
import com.berkat.kitchenstoryowner.databinding.ActivityInformasiRekeningBinding
import com.berkat.kitchenstoryowner.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class InformasiRekeningActivity : AppCompatActivity() {
    lateinit var binding: ActivityInformasiRekeningBinding
    lateinit var preferences: Preferences
    val viewModel: InformasiRekeningViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiRekeningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = Preferences(this)
        val token = preferences.getValues("token").toString()

        binding.back.tvBack.text = "Account Information"
        ArrayAdapter.createFromResource(
            this,
            R.array.bank_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spBankName.adapter = adapter
        }

        binding.btnSave.setOnClickListener {
            viewModel.sendBankData(
                token,
                binding.spBankName.selectedItem.toString(),
                binding.etAccountNumber.text.toString(),
                binding.etAccountName.text.toString()
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.informasiRekening.collect { event ->
                when (event) {
                    is InformasiRekeningViewModel.InformasiRekeningEvent.Success -> {
                        Toast.makeText(
                            this@InformasiRekeningActivity,
                            "Successfully save bank data",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    }
                    is InformasiRekeningViewModel.InformasiRekeningEvent.Failure -> {
                        Toast.makeText(
                            this@InformasiRekeningActivity,
                            event.errorText,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is InformasiRekeningViewModel.InformasiRekeningEvent.Loading -> {

                    }
                    else -> Unit
                }
            }
        }

    }
}