package com.example.foud.models.joke


import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("text")
    val text: String
)