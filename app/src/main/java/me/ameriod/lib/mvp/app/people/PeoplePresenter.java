package me.ameriod.lib.mvp.app.people;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
import me.ameriod.lib.error.Error;
import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.app.api.NetworkErrorHandler;
import me.ameriod.lib.mvp.app.models.PeopleResponse;
import me.ameriod.lib.mvp.app.models.Person;
import me.ameriod.lib.mvp.presenter.rx2.BasePresenterRx2;
import me.ameriod.lib.mvp.presenter.rx2.IObservableSchedulerRx2;

public class PeoplePresenter extends BasePresenterRx2<PeopleContract.View> implements PeopleContract.Presenter {

    @NonNull
    private final PeopleContract.Interactor interactor;

    ArrayList<Person> people;
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
    }

    @Override
    public void restoreState(@NonNull Bundle bundle) {
        super.restoreState(bundle);
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
                            people = new ArrayList<>(peopleResponse.getResults());
                            nextUrl = peopleResponse.getNext();
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
                        people.addAll(peopleResponse.getResults());
                        nextUrl = peopleResponse.getNext();
                        getView().setPeople(people);
                        getView().showProgress(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        getView().displayErrorMessage(new Error.ToastMessage(getErrorHandler().onError(throwable)));
                        getView().showProgress(false);
                    }
                }));
    }
}
