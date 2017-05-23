package me.ameriod.lib.mvp.presenter.rx

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.schedulers.Schedulers

interface IObservableSchedulerRx {

    fun <T> scheduleObservable(): Observable.Transformer<T, T>

    companion object {

        val SUBSCRIBE_COMPUTATION_OBSERVE_ANDROID_MAIN: IObservableSchedulerRx = object : IObservableSchedulerRx {
            override fun <T> scheduleObservable(): Observable.Transformer<T, T> {
                return Observable.Transformer<T, T> { observable ->
                    observable
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                }
            }
        }

        val SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN: IObservableSchedulerRx = object : IObservableSchedulerRx {
            override fun <T> scheduleObservable(): Observable.Transformer<T, T> {
                return Observable.Transformer<T, T> { observable ->
                    observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
            }
        }

        val IO: IObservableSchedulerRx = object : IObservableSchedulerRx {
            override fun <T> scheduleObservable(): Observable.Transformer<T, T> {
                return Observable.Transformer<T, T> { observable ->
                    observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                }
            }
        }

        val COMPUTATION: IObservableSchedulerRx = object : IObservableSchedulerRx {
            override fun <T> scheduleObservable(): Observable.Transformer<T, T> {
                return Observable.Transformer<T, T> { observable ->
                    observable
                            .subscribeOn(Schedulers.computation())
                            .observeOn(Schedulers.computation())
                }
            }
        }

        val IMMEDIATE: IObservableSchedulerRx = object : IObservableSchedulerRx {
            override fun <T> scheduleObservable(): Observable.Transformer<T, T> {
                return Observable.Transformer<T, T> { observable ->
                    observable
                            .subscribeOn(Schedulers.immediate())
                            .observeOn(Schedulers.immediate())
                }
            }
        }

        val TRAMPOLINE: IObservableSchedulerRx = object : IObservableSchedulerRx {
            override fun <T> scheduleObservable(): Observable.Transformer<T, T> {
                return Observable.Transformer<T, T> { observable ->
                    observable
                            .subscribeOn(Schedulers.trampoline())
                            .observeOn(Schedulers.trampoline())
                }
            }
        }
    }
}
