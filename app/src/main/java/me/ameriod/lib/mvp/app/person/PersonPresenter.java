package me.ameriod.lib.mvp.app.person;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import icepick.Icepick;
import icepick.State;
import io.reactivex.functions.Consumer;
import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.app.api.NetworkErrorHandler;
import me.ameriod.lib.mvp.app.models.Person;
import me.ameriod.lib.mvp.presenter.rx2.BasePresenterRx2;
import me.ameriod.lib.mvp.presenter.rx2.IObservableSchedulerRx2;

public class PersonPresenter extends BasePresenterRx2<PersonContract.View> implements PersonContract.Presenter {

    @State
    Person person;
    @NonNull
    private PersonContract.Interactor interactor;

    public PersonPresenter(@NonNull IObservableSchedulerRx2 scheduler,
                           @NonNull Mvp.ErrorHandler errorHandler,
                           @NonNull PersonContract.Interactor interactor) {
        super(scheduler, errorHandler);
        this.interactor = interactor;
    }

    public static PersonPresenter newInstance(@NonNull Context context, @NonNull String id) {
        return new PersonPresenter(IObservableSchedulerRx2.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN,
                new NetworkErrorHandler(context), new PersonInteractor(id));
    }

    @Override
    public void saveState(@NonNull Bundle bundle) {
        super.saveState(bundle);
        Icepick.saveInstanceState(this, bundle);
    }

    @Override
    public void restoreState(@Nullable Bundle bundle) {
        super.restoreState(bundle);
        Icepick.restoreInstanceState(this, bundle);
    }

    @Override
    public void getPerson() {
        if (person == null) {
            getView().showProgress(true);
            addDisposable(interactor.getPerson()
                    .compose(getScheduler().<Person>schedule())
                    .subscribe(new Consumer<Person>() {
                        @Override
                        public void accept(@NonNull Person personInternal) throws Exception {
                            person = personInternal;
                            getView().setPerson(personInternal);
                            getView().showProgress(false);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            getView().displayError(getErrorHandler().onError(throwable));
                            getView().showProgress(false);
                        }
                    }));
        } else {
            getView().setPerson(person);
        }

    }
}
