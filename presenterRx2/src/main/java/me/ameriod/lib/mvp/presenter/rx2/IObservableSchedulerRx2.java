package me.ameriod.lib.mvp.presenter.rx2;

import android.support.annotation.VisibleForTesting;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

public interface IObservableSchedulerRx2 {

    IObservableSchedulerRx2 SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN = new IObservableSchedulerRx2() {
        @Override
        public <T> ObservableTransformer<T, T> schedule() {
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> upstream) {
                    return upstream
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
    };

    @VisibleForTesting
    IObservableSchedulerRx2 TEST = new IObservableSchedulerRx2() {
        @Override
        public <T> ObservableTransformer<T, T> schedule() {
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> upstream) {
                    TestScheduler scheduler = new TestScheduler();
                    return upstream.subscribeOn(scheduler)
                            .observeOn(scheduler);
                }
            };
        }
    };

    <T> ObservableTransformer<T, T> schedule();

}
