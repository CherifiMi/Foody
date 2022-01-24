package com.example.foud.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foud.models.Result
import com.example.foud.util.Constants.Companion.FAV_TABLE

@Entity(tableName = FAV_TABLE)
class FavEntity(var result: Result){
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}