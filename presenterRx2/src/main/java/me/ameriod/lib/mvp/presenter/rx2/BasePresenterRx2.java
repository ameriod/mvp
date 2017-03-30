package me.ameriod.lib.mvp.presenter.rx2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.ameriod.lib.mvp.Mvp;

public class BasePresenterRx2<V extends Mvp.View> implements Mvp.Presenter<V> {

    private V view;
    private CompositeDisposable compositeDisposable;
    @NonNull
    private IObservableSchedulerRx2 scheduler;

    public BasePresenterRx2(@NonNull IObservableSchedulerRx2 scheduler) {
        this.scheduler = scheduler;
    }

    public BasePresenterRx2() {
        this(IObservableSchedulerRx2.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN);
    }

    @Override
    public void attachView(@NonNull V view) {
        this.view = view;
    }

    @Override
    public void restoreState(@Nullable Bundle bundle) {

    }

    @Override
    public void detachView() {
        disposeDisposables();
        this.view = null;
    }

    @Override
    public void saveState(@NonNull Bundle bundle) {

    }

    @NonNull
    public IObservableSchedulerRx2 getScheduler() {
        return scheduler;
    }

    protected void addDisposable(@NonNull Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable(disposable);
        } else {
            compositeDisposable.add(disposable);
        }
    }

    protected void disposeDisposables() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
    }

    @Override
    public V getView() {
        return view;
    }
}
