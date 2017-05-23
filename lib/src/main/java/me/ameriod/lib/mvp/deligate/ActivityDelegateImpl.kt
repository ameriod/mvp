package me.ameriod.lib.mvp.deligate

import android.os.Bundle

import me.ameriod.lib.mvp.Mvp

class ActivityDelegateImpl<V : Mvp.View, out P : Mvp.Presenter<V>>(private val delegateCallback:
                                                                   ActivityDelegateCallback<V, P>) : ActivityDelegate<V, P> {
    private var presenter: P? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        if (presenter == null) {
            presenter = delegateCallback.createPresenter()
        }
        presenter!!.attachView(delegateCallback.getMvpView())
        if (savedInstanceState != null) {
            presenter!!.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        if (outState != null) {
            presenter!!.saveState(outState)
        }
    }

    override fun onDestroy() {
        presenter!!.detachView()
    }

    override fun getPresenter(): P {
        return presenter!!
    }
}
