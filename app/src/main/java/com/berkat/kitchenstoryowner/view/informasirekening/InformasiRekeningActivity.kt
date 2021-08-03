package com.berkat.kitchenstoryowner.view.informasirekening

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.berkat.kitchenstoryowner.R
import com.berkat.kitchenstoryowner.databinding.ActivityInformasiRekeningBinding

class InformasiRekeningActivity : AppCompatActivity() {
    lateinit var binding :ActivityInformasiRekeningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiRekeningBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.back.tvBack.text = "Informasi Rekening"
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
    }
}