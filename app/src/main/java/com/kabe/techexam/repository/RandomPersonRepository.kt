package com.kabe.techexam.repository

import com.kabe.techexam.data.base.BaseRepository
import com.kabe.techexam.data.base.Resource
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.network.RandomPersonService
import javax.inject.Inject

class RandomPersonRepository @Inject constructor(
    private val randomPersonService : RandomPersonService,
) : BaseRepository() {

    suspend fun getRandomPerson() : Resource<List<RandomPerson>> = serviceCall {
        val result = randomPersonService.getRandomPerson()

        result.results
    }
}