package com.example.foud.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val foodDao: FoodDao) {

    fun getFav(): Flow<List<FavEntity>>{
        return foodDao.getFav()
    }

    suspend fun deleteAll(){
        foodDao.deleteAll()
    }
    suspend fun deleteOneFav(favEntity: FavEntity){
        foodDao.deleteOneFav(favEntity)
    }
    suspend fun insertFav(favEntity: FavEntity){
        foodDao.insertFav(favEntity)
    }
}