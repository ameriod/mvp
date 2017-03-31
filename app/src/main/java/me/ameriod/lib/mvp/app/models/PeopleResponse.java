package me.ameriod.lib.mvp.app.models;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * "count": 87,
 * "next": "http://swapi.co/api/people/?page=3",
 * "previous": "http://swapi.co/api/people/?page=1",
 * "results"
 */
@AutoValue
public abstract class PeopleResponse {

    public static TypeAdapter<PeopleResponse> typeAdapter(Gson gson) {
        return new AutoValue_PeopleResponse.GsonTypeAdapter(gson);
    }

    @SerializedName("count")
    public abstract int count();

    @Nullable
    @SerializedName("next")
    public abstract String next();

    @Nullable
    @SerializedName("previous")
    public abstract String previous();

    @Nullable
    @SerializedName("results")
    public abstract List<Person> results();
}
