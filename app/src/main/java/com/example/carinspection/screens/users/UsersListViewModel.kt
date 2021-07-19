package com.example.carinspection.screens.users

import android.app.Application
import androidx.lifecycle.*
import com.example.carinspection.database.CarInspectionDao
import com.example.carinspection.database.CarInspectionDatabase
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
    private var firstUser = MutableLiveData<User?>()
    private val users = database.getAllUsers()

    /**
     * Converted nights to Spanned for displaying.
     */
    var usersString=Transformations.map(users) { users ->
        formatUsers(users, application.resources)
    }


    init {
        //initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            insertDefault();

            firstUser.value = getFirstFromDatabase()
        }
    }

    /**
     *  Handling the case of the stopped app or forgotten recording,
     *  the start and end times will be the same.j
     *
     *  If the start time and end time are not the same, then we do not have an unfinished
     *  recording.
     */
    private suspend fun getFirstFromDatabase(): User? {
        return withContext(Dispatchers.IO) {
            var night = database.firstUser()
            night
        }
    }

    private suspend fun insertDefault(){
        return withContext(Dispatchers.IO) {
            var user=User()
            user.firstName="მთავარი"
            user.lastName="ადმინისტრატორი"
            user.login="Admin"
            user.password="admin1234"
            user.isAdmin=true;
            database.insertUser(user)
        }
    }



}