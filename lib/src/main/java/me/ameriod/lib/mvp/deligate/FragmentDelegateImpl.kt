package me.ameriod.lib.mvp.deligate

import android.os.Bundle

import me.ameriod.lib.mvp.Mvp

class FragmentDelegateImpl<V : Mvp.View, out P : Mvp.Presenter<V>>(private val delegateCallback:
                                                                   FragmentDelegateCallback<V, P>) : FragmentDelegate<V, P> {
    private var presenter: P? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {
        if (presenter == null) {
            presenter = delegateCallback.createPresenter()
        }
        presenter!!.attachView(delegateCallback.getMvpView())
        if (savedInstanceState != null) {
            presenter!!.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter!!.saveState(outState)
    }

    override fun onDestroyView() {
        presenter!!.detachView()
    }

    override fun getPresenter(): P {
        return presenter!!
    }
}
