package me.ameriod.lib.mvp.app.models

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Person(@SerializedName("birth_year")
                  val birthYear: String?,
                  @SerializedName("eye_color")
                  val eyeColor: String?,
                  @SerializedName("films")
                  val films: List<String>?,
                  @SerializedName("gender")
                  val gender: String?,
                  @SerializedName("height")
                  val height: String?,
                  @SerializedName("homeworld")
                  val homeworld: String?,
                  @SerializedName("mass")
                  val mass: String?,
                  @SerializedName("name")
                  val name: String?,
                  @SerializedName("hair_color")
                  val hairColor: String?,
                  @SerializedName("skin_color")
                  val skinColor: String?,
                  @SerializedName("species")
                  val species: List<String>?,
                  @SerializedName("starships")
                  val starships: List<String>?,
                  @SerializedName("url")
                  val url: String?,
                  @SerializedName("vehicles")
                  val vehicles: List<String>?) : Parcelable {

    fun getSpecies(): String {
        val builder = StringBuilder()
        var i = 0
        val size = species!!.size
        while (i < size) {
            builder.append(species[i]).append(" ")
            i++
        }
        return builder.toString()
    }

    fun getVehicles(): String {
        val builder = StringBuilder()
        var i = 0
        val size = vehicles!!.size
        while (i < size) {
            builder.append(vehicles[i]).append(" ")
            i++
        }
        return builder.toString()
    }

    fun getStarShips(): String {
        val builder = StringBuilder()
        var i = 0
        val size = starships!!.size
        while (i < size) {
            builder.append(starships[i]).append(" ")
            i++
        }
        return builder.toString()
    }

    fun getFilms(): String {
        val builder = StringBuilder()
        var i = 0
        val size = films!!.size
        while (i < size) {
            builder.append(films[i]).append(" ")
            i++
        }
        return builder.toString()
    }

    fun getId(): String? {
        return Uri.parse(url).lastPathSegment
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.createStringArrayList(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createStringArrayList(),
            source.createStringArrayList(),
            source.readString(),
            source.createStringArrayList()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(birthYear)
        writeString(eyeColor)
        writeStringList(films)
        writeString(gender)
        writeString(height)
        writeString(homeworld)
        writeString(mass)
        writeString(name)
        writeString(hairColor)
        writeString(skinColor)
        writeStringList(species)
        writeStringList(starships)
        writeString(url)
        writeStringList(vehicles)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Person> = object : Parcelable.Creator<Person> {
            override fun createFromParcel(source: Parcel): Person = Person(source)
            override fun newArray(size: Int): Array<Person?> = arrayOfNulls(size)
        }
    }
}