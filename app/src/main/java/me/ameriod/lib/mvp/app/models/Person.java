package me.ameriod.lib.mvp.app.models;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * "starships": [
 * "http://swapi.co/api/starships/12/",
 * ...
 * ],
 * "url": "http://swapi.co/api/people/1/",
 * "vehicles": [
 * "http://swapi.co/api/vehicles/14/"
 * ...
 * ]
 * }
 */
@AutoValue
public abstract class Person implements Parcelable {

    public static TypeAdapter<Person> typeAdapter(Gson gson) {
        return new AutoValue_Person.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("birth_year")
    public abstract String birthYear();

    @Nullable
    @SerializedName("eye_color")
    public abstract String eyeColor();

    @Nullable
    @SerializedName("films")
    public abstract List<String> films();

    @Nullable
    @SerializedName("gender")
    public abstract String gender();

    @Nullable
    @SerializedName("height")
    public abstract String height();

    @Nullable
    @SerializedName("homeworld")
    public abstract String homeworld();

    @Nullable
    @SerializedName("mass")
    public abstract String mass();

    @Nullable
    @SerializedName("name")
    public abstract String name();

    @Nullable
    @SerializedName("hair_color")
    public abstract String hairColor();

    @Nullable
    @SerializedName("skin_color")
    public abstract String skinColor();

    @Nullable
    @SerializedName("species")
    public abstract List<String> species();

    @Nullable
    @SerializedName("starships")
    public abstract List<String> starships();

    @Nullable
    @SerializedName("url")
    public abstract String url();

    @Nullable
    @SerializedName("vehicles")
    public abstract List<String> vehicles();

    public final String getSpecies() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = species().size(); i < size; i++) {
            builder.append(species().get(i)).append(" ");
        }
        return builder.toString();
    }

    public final String getId() {
        return Uri.parse(url()).getLastPathSegment();
    }
}
