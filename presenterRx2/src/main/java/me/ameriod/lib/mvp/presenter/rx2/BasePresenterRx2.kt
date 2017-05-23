package me.ameriod.lib.mvp.presenter.rx2

import android.os.Bundle

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.ameriod.lib.mvp.Mvp

abstract class BasePresenterRx2<V : Mvp.View>(val scheduler: IObservableSchedulerRx2, val errorHandler: Mvp.ErrorHandler) :
        Mvp.Presenter<V> {

    private var view: V? = null
    private var compositeDisposable: CompositeDisposable? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        disposeDisposables()
        this.view = null
    }

    override fun saveState(outState: Bundle) {

    }

    override fun restoreState(savedState: Bundle) {

    }

    protected fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable(disposable)
        } else {
            compositeDisposable!!.add(disposable)
        }
    }

    protected fun disposeDisposables() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
        compositeDisposable = null
    }

    override fun getView(): V {
        return view!!
    }
}
