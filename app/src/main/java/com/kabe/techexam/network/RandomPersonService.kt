package com.kabe.techexam.network

import com.kabe.techexam.model.response.RandomPersonResponse
import retrofit2.http.GET

interface RandomPersonService {

    @GET("api/?results=10")
    suspend fun getRandomPerson() : RandomPersonResponse
}