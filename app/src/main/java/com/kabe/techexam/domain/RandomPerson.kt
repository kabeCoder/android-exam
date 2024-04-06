package com.kabe.techexam.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Random_Person")
data class RandomPerson(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("gender") var gender: String = "",

    ) : Serializable
