package com.berkat.kitchenstoryowner.view.datausaha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.repository.datausaha.DataUsahaRepository
import com.berkat.kitchenstoryowner.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DataUsahaViewModel @Inject constructor(val repository: DataUsahaRepository) : ViewModel() {
    sealed class DataUsahaEvent {

        class Success(val kitchenResponse: KitchenResponse) : DataUsahaEvent()
        class Failure(val errorText: String) : DataUsahaEvent()
        object Loading : DataUsahaEvent()
        object Empty : DataUsahaEvent()

    }


    private val _dataUsaha =
        MutableStateFlow<DataUsahaViewModel.DataUsahaEvent>(DataUsahaViewModel.DataUsahaEvent.Empty)
    val dataUsaha: StateFlow<DataUsahaViewModel.DataUsahaEvent> = _dataUsaha

    fun checkData(
        token: String
    ) = viewModelScope.launch {
        _dataUsaha.value = DataUsahaViewModel.DataUsahaEvent.Loading

        when (val registerResponse =
            repository.checkData(token)) {

            is Resource.Error -> _dataUsaha.value =
                DataUsahaViewModel.DataUsahaEvent.Failure(registerResponse.message!!)
            is Resource.Success -> {

                val dataRegister = registerResponse.data
                _dataUsaha.value = DataUsahaViewModel.DataUsahaEvent.Success(dataRegister!!)
            }
        }
    }
}