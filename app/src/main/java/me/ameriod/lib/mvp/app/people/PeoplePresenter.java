package me.ameriod.lib.mvp.app.people;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import icepick.Icepick;
import icepick.State;
import io.reactivex.functions.Consumer;
import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.app.api.NetworkErrorHandler;
import me.ameriod.lib.mvp.app.models.PeopleResponse;
import me.ameriod.lib.mvp.app.models.Person;
import me.ameriod.lib.mvp.presenter.rx2.BasePresenterRx2;
import me.ameriod.lib.mvp.presenter.rx2.IObservableSchedulerRx2;

public class PeoplePresenter extends BasePresenterRx2<PeopleContract.View> implements PeopleContract.Presenter {

    @NonNull
    private final PeopleContract.Interactor interactor;

    @State
    ArrayList<Person> people;
    @State
    String nextUrl;

    private String urlLoading;

    public PeoplePresenter(@NonNull IObservableSchedulerRx2 scheduler, @NonNull Mvp.ErrorHandler errorHandler,
                           @NonNull PeopleContract.Interactor interactor) {
        super(scheduler, errorHandler);
        this.interactor = interactor;
    }

    public static PeoplePresenter newInstance(@NonNull Context context) {
        return new PeoplePresenter(IObservableSchedulerRx2.Companion.getSUBSCRIBE_IO_OBSERVE_ANDROID_MAIN(),
                new NetworkErrorHandler(context), new PeopleInteractor());
    }

    @Override
    public void saveState(@NonNull Bundle bundle) {
        super.saveState(bundle);
        Icepick.saveInstanceState(this, bundle);
    }

    @Override
    public void restoreState(@NonNull Bundle bundle) {
        super.restoreState(bundle);
        Icepick.restoreInstanceState(this, bundle);
    }

    @Override
    public void getPeople() {
        if (people == null || people.isEmpty()) {
            getView().showProgress(true);
            addDisposable(interactor.getInitialPersonList()
                    .compose(getScheduler().<PeopleResponse>schedule())
                    .subscribe(new Consumer<PeopleResponse>() {
                        @Override
                        public void accept(@NonNull PeopleResponse peopleResponse) throws Exception {
                            people = new ArrayList<>(peopleResponse.results());
                            nextUrl = peopleResponse.next();
                            getView().setPeople(people);
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
            getView().setPeople(people);
        }
    }

    @Override
    public void getMorePeople() {
        if (nextUrl == null || nextUrl.isEmpty() || people == null || people.isEmpty()) {
            return;
        } else if (urlLoading != null && urlLoading.equals(nextUrl)) {
            return;
        }
        urlLoading = nextUrl;
        getView().showProgress(false);
        addDisposable(interactor.getPagedPersonList(nextUrl)
                .compose(getScheduler().<PeopleResponse>schedule())
                .subscribe(new Consumer<PeopleResponse>() {
                    @Override
                    public void accept(@NonNull PeopleResponse peopleResponse) throws Exception {
                        people.addAll(peopleResponse.results());
                        nextUrl = peopleResponse.next();
                        getView().setPeople(people);
                        getView().showProgress(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        getView().displayError(getErrorHandler().onError(throwable));
                        getView().showProgress(false);
                    }
                }));
    }
}
