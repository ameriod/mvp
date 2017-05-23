package me.ameriod.lib.mvp.deligate

import android.os.Bundle
import android.os.Parcelable

import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.view.ViewSavedState

class ViewGroupDelegateImpl<V : Mvp.View, out P : Mvp.Presenter<V>>(private val callback:
                                                                    ViewGroupMvpDelegateCallback<V, P>) : ViewMvpDelegate<V, P> {

    private var savedInstanceState: Bundle? = null
    private var presenter: P? = null

    override fun onAttachedToWindow() {
        if (presenter == null) {
            presenter = callback.createPresenter()
        }
        if (savedInstanceState != null) {
            presenter!!.restoreState(savedInstanceState!!)
        }
        presenter!!.attachView(callback.getMvpView())
    }

    override fun onDetachedFromWindow() {
        presenter!!.detachView()
    }

    override fun onSaveInstanceState(): Parcelable {
        val savedState = ViewSavedState(callback.superOnSaveInstanceState())
        val state = Bundle()
        savedState.state = state
        presenter!!.saveState(state)
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state !is ViewSavedState) {
            callback.superOnRestoreInstanceState(state)
            return
        }
        val savedState = state
        callback.superOnRestoreInstanceState(savedState.superState)
        savedInstanceState = savedState.state
    }

    override fun getPresenter(): P {
        return presenter!!
    }
}

