package com.fascinate.roomdatabasepractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface UsersDao {

    //Use suspend method as we can call them or override them inside the COROUTINE, so it runs on Background thread
    @Insert
    fun insertUser(usersEntity: UsersEntity): Long

    @Update
    suspend fun updateUser(usersEntity: UsersEntity)

    @Delete
    suspend fun deleteUsers(usersEntity: UsersEntity)

    @Query("Select * from Users")
    fun getAllUsers() : List<UsersEntity>
    //As it is the live data so it automatically runs on Background thread so we don't need coroutines
}