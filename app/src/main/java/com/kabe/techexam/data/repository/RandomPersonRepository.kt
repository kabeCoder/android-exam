package com.kabe.techexam.data.repository

import com.kabe.techexam.data.base.BaseRepository
import com.kabe.techexam.data.base.Resource
import com.kabe.techexam.data.base.Status
import com.kabe.techexam.data.database.AppDatabase
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.data.network.RandomPersonService
import javax.inject.Inject

class RandomPersonRepository @Inject constructor(
    private val randomPersonService : RandomPersonService,
    private val appDatabase: AppDatabase
) : BaseRepository() {

    suspend fun getRandomPerson() : Resource<List<RandomPerson>> = serviceCall {
        val result = randomPersonService.getRandomPerson()

        appDatabase.randomPersonDao().insertCurrentRandomPerson(result.results)

        result.results
    }

    suspend fun getCachedRandomPerson(): List<RandomPerson> {
        return appDatabase.randomPersonDao().getAllCurrentRandomPerson()
    }
}
