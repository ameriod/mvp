package me.ameriod.lib.mvp.presenter.rx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.ameriod.lib.mvp.Mvp;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenterRx<V extends Mvp.View> implements Mvp.Presenter<V> {

    private V view;
    private CompositeSubscription compositeSubscription;
    @NonNull
    private IObservableSchedulerRx scheduler;

    public BasePresenterRx(@NonNull IObservableSchedulerRx scheduler) {
        this.scheduler = scheduler;
    }

    public BasePresenterRx() {
        this(IObservableSchedulerRx.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN);
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
        unsubscribeSubscriptions();
        this.view = null;
    }

    @Override
    public void saveState(@NonNull Bundle bundle) {

    }

    @NonNull
    public IObservableSchedulerRx getScheduler() {
        return scheduler;
    }

    protected void addSubscription(@NonNull Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription(subscription);
        } else {
            compositeSubscription.add(subscription);
        }
    }

    protected void unsubscribeSubscriptions() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        compositeSubscription = null;
    }

    @Override
    public V getView() {
        return view;
    }
}
