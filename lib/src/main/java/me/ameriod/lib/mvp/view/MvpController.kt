package me.ameriod.lib.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bluelinelabs.conductor.Controller

import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.deligate.ControllerDelegate
import me.ameriod.lib.mvp.deligate.ControllerDelegateCallback
import me.ameriod.lib.mvp.deligate.ControllerDelegateImpl

abstract class MvpController<V : Mvp.View, P : Mvp.Presenter<V>> : Controller, Mvp.View,
        ControllerDelegateCallback<V, P> {

    private var controllerDelegate: ControllerDelegate<V, P>? = null

    constructor() : super()

    constructor(args: Bundle?) : super(args)

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        if (controllerDelegate == null) {
            controllerDelegate = ControllerDelegateImpl(this)
        }
        controllerDelegate!!.onCreateView()
        onPostCreateView(view, container)
        return view
    }

    protected open fun onPostCreateView(view: View, container: ViewGroup) {}

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        controllerDelegate!!.onRestoreViewState(savedViewState)
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        controllerDelegate!!.onSaveViewState(outState)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        controllerDelegate!!.onDestroyView()
    }

    abstract override fun createPresenter(): P

    override fun getPresenter(): P {
        return controllerDelegate!!.getPresenter()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getMvpView(): V {
        return this as V
    }
}
