package com.example.foud.models


import com.google.gson.annotations.SerializedName

data class RecipesList(
    @SerializedName("results")
    val results: List<Result>,
)