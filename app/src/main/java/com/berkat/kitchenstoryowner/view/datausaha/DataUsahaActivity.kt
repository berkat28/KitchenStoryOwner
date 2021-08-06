package com.berkat.kitchenstoryowner.view.datausaha

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.berkat.kitchenstoryowner.R
import com.berkat.kitchenstoryowner.databinding.ActivityDataUsahaBinding
import com.berkat.kitchenstoryowner.utils.Preferences
import com.berkat.kitchenstoryowner.view.identitaspemilik.IdentitasPemilikActivity
import com.berkat.kitchenstoryowner.view.informasirekening.InformasiRekeningActivity
import com.berkat.kitchenstoryowner.view.syaratdanketentuan.SyaratDanKetentuanActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DataUsahaActivity : AppCompatActivity() {

    val viewModel: DataUsahaViewModel by viewModels()
    lateinit var binding: ActivityDataUsahaBinding
    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataUsahaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = Preferences(this)
        binding.back.ivBack.setOnClickListener { onBackPressed() }
        binding.back.tvBack.text = "Business data"
        val tokenJwt: String = preferences.getValues("token").toString()

        viewModel.checkData(tokenJwt)

        binding.clAccountInformation.setOnClickListener {
            Intent(this,InformasiRekeningActivity::class.java).also { startActivity(it) }
        }
        binding.clOwnerIdentity.setOnClickListener {
            Intent(this,IdentitasPemilikActivity::class.java).also { startActivity(it) }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.dataUsaha.collect { event ->
                when (event) {
                    is DataUsahaViewModel.DataUsahaEvent.Success -> {
                        binding.btnContinue.setBackgroundColor(resources.getColor(R.color.blue))
                        binding.btnContinue.isEnabled = true
                        binding.btnContinue.setOnClickListener {
                            Intent(
                                this@DataUsahaActivity,
                                SyaratDanKetentuanActivity::class.java
                            ).also { startActivity(it) }
                        }
                    }
                    is DataUsahaViewModel.DataUsahaEvent.Failure -> {
                        binding.btnContinue.setBackgroundColor(resources.getColor(R.color.grey))
                        binding.btnContinue.isEnabled = false
                        binding.btnContinue.setOnClickListener {
                            Toast.makeText(
                                this@DataUsahaActivity,
                                "Please complete the data for continue",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    is DataUsahaViewModel.DataUsahaEvent.Loading -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val tokenJwt: String = preferences.getValues("token").toString()
        viewModel.checkData(tokenJwt)
    }
}