package com.example.foud.database

import androidx.room.TypeConverter
import com.example.foud.models.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodTypeConerter {

    var gson = Gson()

    @TypeConverter
    fun resultToString(result: Result): String{
        return gson.toJson(result)
    }
    @TypeConverter
    fun stringToResult(data: String) :Result{
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }
}