package me.ameriod.lib.mvp.app.api;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import me.ameriod.lib.mvp.app.models.PeopleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SwapiService {
  
  @GET("people")
  Observable<PeopleResponse> getPeople();
  
  @GET("people")
  Observable<PeopleResponse> getNextPeople(@Query("page") @NonNull String page);
  
  @GET("people/{id}")
  Observable<PeopleResponse> getPerson(@NonNull String id);

}