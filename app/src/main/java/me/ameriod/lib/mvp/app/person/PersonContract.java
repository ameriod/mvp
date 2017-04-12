package me.ameriod.lib.mvp.app.person;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.app.models.Person;

public class PersonContract {

    public interface Interactor {

        Observable<Person> getPerson();

    }

    public interface Presenter extends Mvp.Presenter<View> {

        void getPerson();

    }

    public interface View extends Mvp.View {

        void setPerson(@NonNull Person person);
    }
}
