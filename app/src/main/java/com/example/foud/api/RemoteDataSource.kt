package com.example.foud.api

import com.example.foud.models.RecipesList
import com.example.foud.models.joke.Joke
import retrofit2.Response
import retrofit2.http.QueryMap
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val recipesApi: RecipesApi) {
    suspend fun getRecipesList(queries: Map<String, String>) : Response<RecipesList>{
        return recipesApi.getRecipesList(queries)
    }
    suspend fun getJoke(queries: Map<String, String>) : Joke{
        return recipesApi.getJoke(queries)
    }
}