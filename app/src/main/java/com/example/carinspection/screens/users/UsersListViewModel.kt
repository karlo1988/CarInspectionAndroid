package com.example.carinspection.screens.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.carinspection.database.CarInspectionDao
import com.example.carinspection.database.User
import com.example.carinspection.helpers.formatUsers
import kotlinx.coroutines.*

class UsersListViewModel(
        val database: CarInspectionDao,
        application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private val users = database.getAllUsers()

    val usersString = Transformations.map(users) { users ->
        formatUsers(users, application.resources)
    }

    init {

    }

    fun onInsert(){
        uiScope.launch {
            val user=User();
            onCreateUser(user)
        }
    }
     private suspend fun onCreateUser(user: User){
        withContext(Dispatchers.IO) {
            database.insertUser(user)
        }
    }

}