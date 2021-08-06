package com.berkat.kitchenstoryowner.view.informasirekening

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.repository.informasirekening.InformasiRekeningRepository
import com.berkat.kitchenstoryowner.utils.Resource
import com.berkat.kitchenstoryowner.view.datausaha.DataUsahaViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InformasiRekeningViewModel @Inject constructor(val repository: InformasiRekeningRepository) :
    ViewModel() {

    sealed class InformasiRekeningEvent {

        class Success(val kitchenResponse: KitchenResponse) : InformasiRekeningEvent()
        class Failure(val errorText: String) : InformasiRekeningEvent()
        object Loading : InformasiRekeningEvent()
        object Empty : InformasiRekeningEvent()

    }

    private val _informasiRekening =
        MutableStateFlow<InformasiRekeningViewModel.InformasiRekeningEvent>(
            InformasiRekeningViewModel.InformasiRekeningEvent.Empty
        )
    val informasiRekening: StateFlow<InformasiRekeningViewModel.InformasiRekeningEvent> =
        _informasiRekening

    fun sendBankData(
        token: String,
        bank_name:String,
        account_number:String,
        account_name:String
    ) = viewModelScope.launch {
        _informasiRekening.value = InformasiRekeningEvent.Loading

        when (val registerResponse =
            repository.sendBankData(token,bank_name, account_number, account_name)) {

            is Resource.Error -> _informasiRekening.value =
                InformasiRekeningEvent.Failure(registerResponse.message!!)
            is Resource.Success -> {

                val dataRegister = registerResponse.data
                _informasiRekening.value = InformasiRekeningEvent.Success(dataRegister!!)
            }
        }
    }
}