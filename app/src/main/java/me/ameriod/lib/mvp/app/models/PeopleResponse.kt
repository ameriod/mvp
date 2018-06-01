package me.ameriod.lib.mvp.app.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * "count": 87,
 * "next": "http://swapi.co/api/people/?page=3",
 * "previous": "http://swapi.co/api/people/?page=1",
 * "results"
 */
data class PeopleResponse(@SerializedName("count")
                          val count: Int,
                          @SerializedName("next")
                          val next: String,
                          @SerializedName("previous")
                          val previous: String,

                          @SerializedName("results")
                          val results: List<Person>) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(Person.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(count)
        writeString(next)
        writeString(previous)
        writeTypedList(results)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PeopleResponse> = object : Parcelable.Creator<PeopleResponse> {
            override fun createFromParcel(source: Parcel): PeopleResponse = PeopleResponse(source)
            override fun newArray(size: Int): Array<PeopleResponse?> = arrayOfNulls(size)
        }
    }
}
