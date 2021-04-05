package com.example.carinspection.screens.login

import android.view.View
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.carinspection.R

class LoginViewModel: ViewModel () {

    private val LOGIN: String="Admin"
    private val PASS: String="1234"

    var login=""
    var password=""

    private val _errorText=MutableLiveData<String>()
    val errorText: LiveData<String>
        get() =_errorText

    private val _authorized=MutableLiveData<Boolean>()
    val authorized: LiveData<Boolean>
        get() =_authorized

    private val _failed=MutableLiveData<Boolean>()
    val failed: LiveData<Boolean>
        get() =_failed

    init {
        _authorized.value = false
        _failed.value=false
    }
    fun onSignIn() {

        if (login.isNullOrEmpty()) {
            _errorText.value = "მიუთითეთ შესვლის სახელი!"
            _authorized.value = false
            _failed.value=true
            return
        }

        if (password.isNullOrEmpty()) {
            _errorText.value = "მიუთითეთ პაროლი!"
            _authorized.value = false
            _failed.value=true
            return
        }

        if (login != LOGIN || password != PASS) {
            _errorText.value = "შესვლის სახელი ან პაროლი არასწორია!"
            _authorized.value = false
            _failed.value=true
            return
        }

        _authorized.value = true
        _failed.value=false
    }

    fun signedIn(){
        login=""
        password=""
        _errorText.value=""
        _authorized.value = false
        _failed.value = false
    }
    override fun onCleared() {
        super.onCleared()

    }
}