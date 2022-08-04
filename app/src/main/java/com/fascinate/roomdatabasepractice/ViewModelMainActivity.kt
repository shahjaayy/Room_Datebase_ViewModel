package com.fascinate.roomdatabasepractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ViewModelMainActivity(app: Application): AndroidViewModel(app) {

    private var allUsers: MutableLiveData<List<UsersEntity>> = MutableLiveData()

    fun getAllUsersObservers(): MutableLiveData<List<UsersEntity>>
    {
        return allUsers
    }

    fun insertUser(entity: UsersEntity): Long {
        val database = UsersDatabase.getDatabase(getApplication()).userDao()
        val check = database.insertUser(entity)
        getAllUsers()

        return check
    }

    private fun getAllUsers(){
        val database = UsersDatabase.getDatabase(getApplication()).userDao()
        val list = database.getAllUsers()

        allUsers.postValue(list)
    }

}