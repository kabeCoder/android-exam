package com.kabe.techexam.data.network

import com.kabe.techexam.data.model.response.RandomPersonResponse
import retrofit2.http.GET

interface RandomPersonService {

    @GET("api/?results=10")
    suspend fun getRandomPerson() : RandomPersonResponse
}