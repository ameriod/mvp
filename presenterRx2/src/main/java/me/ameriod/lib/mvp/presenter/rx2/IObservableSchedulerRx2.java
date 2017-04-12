package me.ameriod.lib.mvp.presenter.rx2;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

public interface IObservableSchedulerRx2 {

    <T> ObservableTransformer<T, T> schedule();


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

    IObservableSchedulerRx2 SUBSCRIBE_COMPUTATION_OBSERVE_ANDROID_MAIN = new IObservableSchedulerRx2() {
        @Override
        public <T> ObservableTransformer<T, T> schedule() {
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> upstream) {
                    return upstream
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
    };

    IObservableSchedulerRx2 TRAMPOLINE = new IObservableSchedulerRx2() {
        @Override
        public <T> ObservableTransformer<T, T> schedule() {
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> upstream) {
                    TestScheduler scheduler = new TestScheduler();
                    return upstream.subscribeOn(Schedulers.trampoline())
                            .observeOn(Schedulers.trampoline());
                }
            };
        }
    };

    IObservableSchedulerRx2 COMPUTATION = new IObservableSchedulerRx2() {
        @Override
        public <T> ObservableTransformer<T, T> schedule() {
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> upstream) {
                    return upstream.subscribeOn(Schedulers.computation())
                            .observeOn(Schedulers.computation());
                }
            };
        }
    };

    IObservableSchedulerRx2 IO = new IObservableSchedulerRx2() {
        @Override
        public <T> ObservableTransformer<T, T> schedule() {
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> upstream) {
                    return upstream.subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io());
                }
            };
        }
    };

}
