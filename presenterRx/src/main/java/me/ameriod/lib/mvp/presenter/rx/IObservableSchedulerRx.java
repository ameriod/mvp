package me.ameriod.lib.mvp.presenter.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public interface IObservableSchedulerRx {

    <T> Observable.Transformer<T, T> scheduleObservable();

    Action0 DECREMENT = new Action0() {
        @Override
        public void call() {
            RxEspresso.getInstance().decrement();
        }
    };
    Action0 INCREMENT = new Action0() {
        @Override
        public void call() {
            RxEspresso.getInstance().increment();
        }
    };

    IObservableSchedulerRx SUBSCRIBE_COMPUTATION_OBSERVE_ANDROID_MAIN = new IObservableSchedulerRx() {
        @Override
        public <T> Observable.Transformer<T, T> scheduleObservable() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate(DECREMENT)
                            .doOnSubscribe(INCREMENT);
                }
            };
        }
    };

    IObservableSchedulerRx SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN = new IObservableSchedulerRx() {
        @Override
        public <T> Observable.Transformer<T, T> scheduleObservable() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate(DECREMENT)
                            .doOnSubscribe(INCREMENT);
                }
            };
        }
    };

    IObservableSchedulerRx IO = new IObservableSchedulerRx() {
        @Override
        public <T> Observable.Transformer<T, T> scheduleObservable() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .doAfterTerminate(DECREMENT)
                            .doOnSubscribe(INCREMENT);
                }
            };
        }
    };

    IObservableSchedulerRx COMPUTATION = new IObservableSchedulerRx() {
        @Override
        public <T> Observable.Transformer<T, T> scheduleObservable() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.computation())
                            .observeOn(Schedulers.computation())
                            .doAfterTerminate(DECREMENT)
                            .doOnSubscribe(INCREMENT);
                }
            };
        }
    };

    IObservableSchedulerRx IMMEDIATE = new IObservableSchedulerRx() {
        @Override
        public <T> Observable.Transformer<T, T> scheduleObservable() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.immediate())
                            .observeOn(Schedulers.immediate())
                            .doAfterTerminate(DECREMENT)
                            .doOnSubscribe(INCREMENT);
                }
            };
        }
    };

    IObservableSchedulerRx TRAMPOLINE = new IObservableSchedulerRx() {
        @Override
        public <T> Observable.Transformer<T, T> scheduleObservable() {
            return new Observable.Transformer<T, T>() {
                @Override
                public Observable<T> call(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.trampoline())
                            .observeOn(Schedulers.trampoline())
                            .doAfterTerminate(DECREMENT)
                            .doOnSubscribe(INCREMENT);
                }
            };
        }
    };
}
