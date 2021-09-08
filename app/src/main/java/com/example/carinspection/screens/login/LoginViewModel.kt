package com.example.carinspection.screens.login

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.carinspection.R
import com.example.carinspection.database.CarInspectionDao
import com.example.carinspection.database.User
import kotlinx.coroutines.*

class LoginViewModel(val database: CarInspectionDao,
                     application: Application): ViewModel () {

    private val LOGIN: String="Admin"
    private val PASS: String="1234"
    /*private var viewModelJob = Job()
    private var user = MutableLiveData<User?>()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)*/

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

       /* uiScope.launch {
            user.value=getUser(login)
        }*/

        val user=database.getUser(login);

        if(user==null){
            _errorText.value = "შესვლის სახელი ან პაროლი არასწორია!"
            _authorized.value = false
            _failed.value=true
            return
        }



        if ( password != user.password) {
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
       // viewModelJob.cancel()

    }
    /*private suspend fun getUser(login: String): User? {
        return withContext(Dispatchers.IO) {
            var user = database.getUser(login)
            user
        }
    }*/
}