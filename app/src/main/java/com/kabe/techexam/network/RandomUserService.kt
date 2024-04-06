package com.kabe.techexam.network

import com.kabe.techexam.model.response.RandomPersonResponse
import retrofit2.http.GET

interface RandomUserService {

    @GET("api/")
    suspend fun getRandomPerson() : RandomPersonResponse
}