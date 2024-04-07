package com.kabe.techexam.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kabe.techexam.domain.RandomPerson

@Dao
abstract class RandomPersonDao {
    @Query("SELECT * FROM Random_Person")
    abstract suspend fun getAllCurrentRandomPerson(): List<RandomPerson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCurrentRandomPerson(randomPerson: List<RandomPerson>)

    @Query("DELETE FROM Random_Person")
    abstract suspend fun deleteAllRandomPerson()
}