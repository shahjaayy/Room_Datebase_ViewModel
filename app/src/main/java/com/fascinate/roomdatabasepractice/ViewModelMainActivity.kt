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

}