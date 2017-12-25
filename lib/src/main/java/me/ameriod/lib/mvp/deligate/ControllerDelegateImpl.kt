package me.ameriod.lib.mvp.deligate

import android.os.Bundle

import me.ameriod.lib.mvp.Mvp

class ControllerDelegateImpl<V : Mvp.View, out P : Mvp.Presenter<V>>(private val delegateCallback:
                                                                     ControllerDelegateCallback<V, P>) : ControllerDelegate<V, P> {
    private var presenter: P? = null

    override fun onCreateView() {
        if (presenter == null) {
            presenter = delegateCallback.createPresenter()
        }
        presenter!!.attachView(delegateCallback.getMvpView())
    }

    override fun onRestoreViewState(savedViewState: Bundle) {
        presenter!!.restoreState(savedViewState)
    }

    override fun onSaveViewState(outState: Bundle) {
        presenter!!.saveState(outState)
    }

    override fun onDestroyView() {
        presenter!!.detachView()
    }

    override fun getPresenter(): P {
        return presenter!!
    }
}
