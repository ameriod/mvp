package me.ameriod.lib.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.ameriod.lib.mvp.scheduler.IObservableScheduler;
import me.ameriod.lib.mvp.Mvp;

public class BasePresenter<T extends Mvp.View> implements Mvp.Presenter<T> {

    private T view;
    private CompositeDisposable compositeDisposable;
    @NonNull
    private IObservableScheduler scheduler;

    public BasePresenter(@NonNull IObservableScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public BasePresenter() {
        this(IObservableScheduler.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN);
    }

    @Override
    public void attachView(@NonNull T view) {
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
    public IObservableScheduler getScheduler() {
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

    protected T getView() {
        return view;
    }
}
