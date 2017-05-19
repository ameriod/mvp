package me.ameriod.lib.mvp.app.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import me.ameriod.lib.mvp.app.models.PeopleResponse;
import me.ameriod.lib.mvp.app.models.Person;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * TODO make this better
 */
public class SwapiClient implements SwapiService {

    private final SwapiService service;

    public SwapiClient(@NonNull SwapiService service) {
        this.service = service;
    }

    public SwapiClient() {
        this(new Retrofit.Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                .baseUrl("http://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapterFactory(SwapiAdapterFactory.create())
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(SwapiService.class));
    }

    @Override
    public Observable<PeopleResponse> getPeople() {
        return service.getPeople();
    }

    @Override
    public Observable<PeopleResponse> getNextPeople(@Query("page") @NonNull String page) {
        return service.getNextPeople(page);
    }

    @Override
    public Observable<Person> getPerson(@NonNull String id) {
        return service.getPerson(id);
    }
}
