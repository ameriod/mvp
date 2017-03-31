package me.ameriod.lib.mvp.app.people;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.app.models.PeopleResponse;
import me.ameriod.lib.mvp.app.models.Person;

public class PeopleContract {

    public interface View extends Mvp.View {
        void setPeople(List<Person> people);
    }

    public interface Presenter extends Mvp.Presenter<View> {
         void getPeople();
    }

    public interface Interactor {

        Observable<PeopleResponse> getInitialPersonList();

        Observable<PeopleResponse> getPagedPersonList(@NonNull String nextUrl);
    }
}
