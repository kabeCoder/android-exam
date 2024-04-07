package com.kabe.techexam.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kabe.techexam.data.database.dao.RandomPersonDao
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.util.RandomPersonConverter

@Database(
    entities = [RandomPerson::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RandomPersonConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun randomPersonDao(): RandomPersonDao
}