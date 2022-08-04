package com.fascinate.roomdatabasepractice

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Users")
data class UsersEntity (

    @PrimaryKey(autoGenerate = true)
    val uId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val date: Date

    )