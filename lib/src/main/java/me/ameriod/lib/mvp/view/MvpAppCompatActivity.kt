package me.ameriod.lib.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.deligate.ActivityDelegate
import me.ameriod.lib.mvp.deligate.ActivityDelegateCallback
import me.ameriod.lib.mvp.deligate.ActivityDelegateImpl

abstract class MvpAppCompatActivity<V : Mvp.View, out P : Mvp.Presenter<V>> : AppCompatActivity(), Mvp.View,
        ActivityDelegateCallback<V, P> {

    private var activityDelegate: ActivityDelegate<V, P>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activityDelegate == null) {
            activityDelegate = ActivityDelegateImpl(this)
        }
        activityDelegate!!.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityDelegate!!.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityDelegate!!.onDestroy()
    }

    override fun getPresenter(): P {
        return activityDelegate!!.getPresenter()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getMvpView(): V {
        return this as V
    }
}

