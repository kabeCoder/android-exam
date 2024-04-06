package com.kabe.techexam.model.response

import com.google.gson.annotations.SerializedName
import com.kabe.techexam.domain.RandomPerson

data class RandomPersonResponse (
    @SerializedName("results") var results: List<RandomPerson>
)