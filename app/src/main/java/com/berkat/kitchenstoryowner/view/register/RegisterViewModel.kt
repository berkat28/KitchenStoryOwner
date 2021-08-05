package com.berkat.kitchenstoryowner.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkat.kitchenstoryowner.model.KitchenResponse
import com.berkat.kitchenstoryowner.repository.register.RegisterRepository
import com.berkat.kitchenstoryowner.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val repository: RegisterRepository) : ViewModel() {

    sealed class RegisterEvent {

        class Success(val kitchenResponse: KitchenResponse) : RegisterEvent()
        class Failure(val errorText: String) : RegisterEvent()
        object Loading : RegisterEvent()
        object Empty : RegisterEvent()

    }

    private val _register =
        MutableStateFlow<RegisterEvent>(RegisterEvent.Empty)
    val registerUser: StateFlow<RegisterEvent> = _register


    fun register(
        fullname: String,
        email: String,
        password: String,
        brand_name: String,
    ) = viewModelScope.launch {
        _register.value = RegisterEvent.Loading

        when (val registerResponse =
            repository.register(fullname, email, password, brand_name)) {

            is Resource.Error -> _register.value = RegisterEvent.Failure(registerResponse.message!!)
            is Resource.Success -> {

                val dataRegister = registerResponse.data
                _register.value = RegisterEvent.Success(dataRegister!!)
            }
        }
    }

}