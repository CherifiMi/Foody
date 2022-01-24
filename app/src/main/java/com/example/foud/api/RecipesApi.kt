package com.example.foud.api

import com.example.foud.models.RecipesList
import com.example.foud.models.joke.Joke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecipesApi {
    @GET("/recipes/complexSearch")
    suspend fun getRecipesList(@QueryMap queries: Map<String, String>): Response<RecipesList>

    @GET("/food/jokes/random")
    suspend fun getJoke(@QueryMap queries: Map<String, String>): Joke
}