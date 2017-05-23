package me.ameriod.lib.mvp.presenter.rx

import android.os.Bundle

import me.ameriod.lib.mvp.Mvp
import rx.Subscription
import rx.subscriptions.CompositeSubscription

abstract class BasePresenterRx<V : Mvp.View>(val scheduler: IObservableSchedulerRx, val errorHandler: Mvp
.ErrorHandler) : Mvp.Presenter<V> {

    private var view: V? = null
    private var compositeSubscription: CompositeSubscription? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        unSubscribeSubscriptions()
        this.view = null
    }

    override fun saveState(outState: Bundle) {

    }

    override fun restoreState(savedState: Bundle) {

    }
    protected fun addSubscription(subscription: Subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = CompositeSubscription(subscription)
        } else {
            compositeSubscription!!.add(subscription)
        }
    }

    protected fun unSubscribeSubscriptions() {
        if (compositeSubscription != null && !compositeSubscription!!.isUnsubscribed) {
            compositeSubscription!!.unsubscribe()
        }
        compositeSubscription = null
    }

    override fun getView(): V {
        return view!!
    }
}
