package me.ameriod.lib.mvp.app.people;

import android.support.annotation.NonNull;

import io.reactivex.functions.Consumer;
import me.ameriod.lib.mvp.app.models.PeopleResponse;
import me.ameriod.lib.mvp.presenter.rx2.BasePresenterRx2;
import me.ameriod.lib.mvp.presenter.rx2.IObservableSchedulerRx2;
import timber.log.Timber;

public class PeoplePresenter extends BasePresenterRx2<PeopleContract.View> implements PeopleContract.Presenter {
    @NonNull
    private final PeopleContract.Interactor interactor;

    public static PeoplePresenter newInstance() {
        return new PeoplePresenter(IObservableSchedulerRx2.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN, new PeopleInteractor());
    }

    public PeoplePresenter(@NonNull IObservableSchedulerRx2 scheduler, @NonNull PeopleContract.Interactor interactor) {
        super(scheduler);
        this.interactor = interactor;
    }

    @Override
    public void getPeople() {
        addDisposable(interactor.getInitialPersonList()
                .compose(getScheduler().<PeopleResponse>schedule())
                .subscribe(new Consumer<PeopleResponse>() {
                    @Override
                    public void accept(@NonNull PeopleResponse peopleResponse) throws Exception {
                        Timber.d("people %d", peopleResponse.results().size());
                        getView().setPeople(peopleResponse.results());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Timber.e(throwable, "Error getting people");
                    }
                }));
    }
}
