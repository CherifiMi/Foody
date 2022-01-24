package com.example.foud.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(favEntity: FavEntity)

    @Query("SELECT * FROM favorites_table ORDER BY id ASC")
    fun getFav(): Flow<List<FavEntity>>

    @Query("DELETE FROM favorites_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteOneFav(favEntity: FavEntity)
}