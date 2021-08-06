package com.berkat.kitchenstoryowner.view.identitaspemilik

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.berkat.kitchenstoryowner.databinding.ActivityIdentitasPemilikBinding
import com.berkat.kitchenstoryowner.utils.Preferences
import com.berkat.kitchenstoryowner.utils.UploadRequestBody
import com.berkat.kitchenstoryowner.utils.getFileName
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@AndroidEntryPoint
class IdentitasPemilikActivity : AppCompatActivity(), UploadRequestBody.UploadCallback {
    lateinit var binding: ActivityIdentitasPemilikBinding
    lateinit var preferences: Preferences
    val viewModel: IdentitasPemilikViewModel by viewModels()
    private val INTENT_REQUEST_CODE = 100
    var imageUrl: Uri? = null
    var tokenNotif: RequestBody? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentitasPemilikBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivKtp.setOnClickListener {
            openImageChooser()
        }

        binding.back.tvBack.text = "Identity Owner"
        binding.back.ivBack.setOnClickListener { onBackPressed() }
        binding.btnSave.setOnClickListener {
            //untuk upload image
            val parcelFileDescriptor =
                contentResolver.openFileDescriptor(imageUrl!!, "r", null)
                    ?: return@setOnClickListener
            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
            val file = File(cacheDir, contentResolver.getFileName(imageUrl!!))
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            val body = UploadRequestBody(file, "image", this)

            val ccd = binding.ccdNomor.selectedCountryCode.toString()
            val phone = binding.etNoHp.text.toString()
            val phoneNumber = (ccd + phone).toRequestBody("text/plain".toMediaTypeOrNull())
            val nameId = toRequestBody(binding.etNameId)
            val nik = toRequestBody(binding.etNumberId)
            viewModel.sendIdentitas(
                phoneNumber, nik, nameId, MultipartBody.Part.createFormData(
                    "photo",
                    file.name,
                    body
                )
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.informasiRekening.collect { event ->
                when (event) {
                    is IdentitasPemilikViewModel.IdentitasPemilikEvent.Success -> {
                        Toast.makeText(
                            this@IdentitasPemilikActivity,
                            "Successfully save data",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    }
                    is IdentitasPemilikViewModel.IdentitasPemilikEvent.Failure -> {
                        Toast.makeText(
                            this@IdentitasPemilikActivity,
                            event.errorText,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is IdentitasPemilikViewModel.IdentitasPemilikEvent.Loading -> {

                    }
                    else -> Unit
                }
            }
        }
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, INTENT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                INTENT_REQUEST_CODE -> {
                    imageUrl = data?.data
                    binding.ivKtp.setImageURI(imageUrl)
                }
            }
        }
    }

    fun toRequestBody(textInputEditText: TextInputEditText): RequestBody {
        return textInputEditText.text.toString().toRequestBody("text/plain".toMediaTypeOrNull())
    }

    override fun onProgressUpdate(percentage: Int) {}


}