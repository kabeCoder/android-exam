package com.kabe.techexam.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.kabe.techexam.domain.RandomPerson

class RandomPersonConverter {
    // Picture
    @TypeConverter
    fun fromPicture(picture: RandomPerson.Picture?): String? {
        return Gson().toJson(picture)
    }

    @TypeConverter
    fun toPicture(pictureString: String?): RandomPerson.Picture? {
        return Gson().fromJson(pictureString, RandomPerson.Picture::class.java)
    }

    //Name
    @TypeConverter
    fun fromName(name: RandomPerson.Name?): String? {
        return Gson().toJson(name)
    }

    @TypeConverter
    fun toName(nameString: String?): RandomPerson.Name? {
        return Gson().fromJson(nameString, RandomPerson.Name::class.java)
    }

    // Date Of Birth
    @TypeConverter
    fun fromDateOfBirth(dateOfBirth: RandomPerson.DateOfBirth?): String? {
        return Gson().toJson(dateOfBirth)
    }

    @TypeConverter
    fun toDateOfBirth(dateOfBirthString: String?): RandomPerson.DateOfBirth? {
        return Gson().fromJson(dateOfBirthString, RandomPerson.DateOfBirth::class.java)
    }

    // Location
    @TypeConverter
    fun fromLocation(location: RandomPerson.Location?): String? {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun toLocation(locationString: String?): RandomPerson.Location? {
        return Gson().fromJson(locationString, RandomPerson.Location::class.java)
    }

    // Street
    @TypeConverter
    fun fromStreet(street: RandomPerson.Location.Street?): String? {
        return Gson().toJson(street)
    }

    @TypeConverter
    fun toStreet(streetString: String?): RandomPerson.Location.Street? {
        return Gson().fromJson(streetString, RandomPerson.Location.Street::class.java)
    }

    // Contact Person
    @TypeConverter
    fun fromContactPerson(contactPerson: RandomPerson.ContactPerson?): String? {
        return Gson().toJson(contactPerson)
    }

    @TypeConverter
    fun toContactPerson(contactPersonString: String?): RandomPerson.ContactPerson? {
        return Gson().fromJson(contactPersonString, RandomPerson.ContactPerson::class.java)
    }
}