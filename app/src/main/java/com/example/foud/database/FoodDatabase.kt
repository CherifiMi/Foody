package com.example.foud.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavEntity::class], version = 1, exportSchema = false)
@TypeConverters(FoodTypeConerter::class)
abstract class FoodDatabase: RoomDatabase() {
    abstract fun foodDao(): FoodDao
}