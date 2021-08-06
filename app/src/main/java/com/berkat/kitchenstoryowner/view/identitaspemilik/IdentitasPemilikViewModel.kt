package com.berkat.kitchenstoryowner.view.identitaspemilik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.repository.identitaspemilik.IdentitasPemilikRepository
import com.berkat.kitchenstoryowner.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class IdentitasPemilikViewModel @Inject constructor(val repository: IdentitasPemilikRepository) :
    ViewModel() {

    sealed class IdentitasPemilikEvent {

        class Success(val kitchenResponse: KitchenResponse) : IdentitasPemilikEvent()
        class Failure(val errorText: String) : IdentitasPemilikEvent()
        object Loading : IdentitasPemilikEvent()
        object Empty : IdentitasPemilikEvent()

    }

    private val _identitasPemilik =
        MutableStateFlow<IdentitasPemilikViewModel.IdentitasPemilikEvent>(
            IdentitasPemilikViewModel.IdentitasPemilikEvent.Empty
        )
    val informasiRekening: StateFlow<IdentitasPemilikViewModel.IdentitasPemilikEvent> =
        _identitasPemilik

    fun sendIdentitas(
        phone:RequestBody,
        nik: RequestBody,
        ktp_name: RequestBody,
        ktp_picture: MultipartBody.Part
    ) = viewModelScope.launch {
        _identitasPemilik.value = IdentitasPemilikEvent.Loading

        when (val registerResponse =
            repository.sendIdentitas(phone,nik, ktp_name, ktp_picture)) {

            is Resource.Error -> _identitasPemilik.value =
                IdentitasPemilikEvent.Failure(registerResponse.message!!)
            is Resource.Success -> {

                val dataRegister = registerResponse.data
                _identitasPemilik.value = IdentitasPemilikEvent.Success(dataRegister!!)
            }
        }
    }
}