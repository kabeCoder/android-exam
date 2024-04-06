package com.kabe.techexam.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Random_Person")
data class RandomPerson(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("picture") var picture: Picture? = null,
    @SerializedName("name") var name: Name? = null,
    @SerializedName("dob") var dateOfBirth: DateOfBirth? = null,
    @SerializedName("email") var emailAddress: String = "",
    @SerializedName("phone") var mobileNumber: String = "",
    @SerializedName("location") var address: Location? = null,
    @SerializedName("id") var contactPersonName: ContactPerson? = null,
    @SerializedName("cell") var contactPersonMobileNumber: String = "",
    @SerializedName("gender") var gender: String = "",
    @SerializedName("nat") var nationality: String = ""
) : Serializable {
    data class Picture (
        @SerializedName("large") var largeProfile: String = "",
        @SerializedName("thumbnail") var thumbnailProfile: String = ""
    ) : Serializable
    data class Name (
        @SerializedName("title") var title: String = "",
        @SerializedName("first") var firstName: String = "",
        @SerializedName("last") var lastName: String = ""
    ) : Serializable
    data class DateOfBirth (
        @SerializedName("date") var date: String = "",
        @SerializedName("age") var age: Int?
    ) : Serializable
    data class ContactPerson (
        @SerializedName("name") var name: String = ""
    ) : Serializable

    data class Location (
        @SerializedName("street") var street: Street? = null,
        @SerializedName("city") var city: String = "",
        @SerializedName("state") var state: String = "",
        @SerializedName("country") var country: String = "",
        @SerializedName("postcode") var postCode: String?,
    ) : Serializable {
        data class Street (
            @SerializedName("number") var number: Int?,
            @SerializedName("name") var name: String = ""
        ) : Serializable
    }
}