package me.ameriod.lib.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.deligate.FragmentDelegate
import me.ameriod.lib.mvp.deligate.FragmentDelegateCallback
import me.ameriod.lib.mvp.deligate.FragmentDelegateImpl

abstract class MvpFragment<V : Mvp.View, out P : Mvp.Presenter<V>> : Fragment(), Mvp.View,
        FragmentDelegateCallback<V, P> {

    private var fragmentDelegate: FragmentDelegate<V, P>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (fragmentDelegate == null) {
            fragmentDelegate = FragmentDelegateImpl(this)
        }
        fragmentDelegate!!.onViewCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fragmentDelegate!!.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDelegate!!.onDestroyView()
    }

    abstract override fun createPresenter(): P

    override fun getPresenter(): P {
        return fragmentDelegate!!.getPresenter()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getMvpView(): V {
        return this as V
    }
}
