package me.ameriod.lib.mvp.app.person;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import me.ameriod.lib.mvp.app.api.SwapiClient;
import me.ameriod.lib.mvp.app.models.Person;

public class PersonInteractor implements PersonContract.Interactor {
    @NonNull
    private SwapiClient client;
    @NonNull
    private String id;

    public PersonInteractor(@NonNull SwapiClient client, @NonNull String id) {
        this.client = client;
        this.id = id;
    }

    public PersonInteractor(@NonNull String id) {
        this(new SwapiClient(), id);
    }

    @Override
    public Observable<Person> getPerson() {
        return client.getPerson(id);
    }
}
