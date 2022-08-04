package com.fascinate.roomdatabasepractice

import androidx.room.TypeConverter
import java.util.*

class ConvertsType {

    @TypeConverter
    fun getDateToLong(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun getLongToDate(value: Long): Date{
        return Date(value)
    }
}