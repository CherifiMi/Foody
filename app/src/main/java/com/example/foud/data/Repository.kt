package com.example.foud.data

import com.example.foud.api.RemoteDataSource
import com.example.foud.database.LocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor (
    remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) {
    val remote = remoteDataSource
    val local = localDataSource
}