package com.example.foud.di

import android.content.Context
import androidx.room.Room
import com.example.foud.database.FoodDatabase
import com.example.foud.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(
        context,
        FoodDatabase::class.java,
        DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: FoodDatabase) = database.foodDao()
}