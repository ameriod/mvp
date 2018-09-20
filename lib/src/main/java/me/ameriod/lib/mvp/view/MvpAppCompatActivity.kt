package me.ameriod.lib.mvp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.ameriod.lib.error.Error
import me.ameriod.lib.error.ErrorDisplayDelegate
import me.ameriod.lib.error.ErrorDisplayDelegateImpl

import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.deligate.ActivityDelegate
import me.ameriod.lib.mvp.deligate.ActivityDelegateCallback
import me.ameriod.lib.mvp.deligate.ActivityDelegateImpl

abstract class MvpAppCompatActivity<V : Mvp.View, out P : Mvp.Presenter<V>> : AppCompatActivity(), Mvp.View,
        ActivityDelegateCallback<V, P> {

    private var activityDelegate: ActivityDelegate<V, P>? = null
    private var errorDisplayDelegate : ErrorDisplayDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activityDelegate == null) {
            activityDelegate = ActivityDelegateImpl(this)
        }
        activityDelegate!!.onCreate(savedInstanceState)

        if (errorDisplayDelegate == null) {
            errorDisplayDelegate = ErrorDisplayDelegateImpl()
        }
        errorDisplayDelegate!!.attachView(getErrorRootView())
    }

    open fun getErrorRootView() : View = findViewById(android.R.id.content) ?: window.decorView.findViewById(android.R.id.content)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityDelegate!!.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityDelegate!!.onDestroy()
        errorDisplayDelegate!!.detachView()
    }

    override fun getPresenter(): P {
        return activityDelegate!!.getPresenter()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getMvpView(): V {
        return this as V
    }

    fun getErrorDisplatDelegate() : ErrorDisplayDelegate = errorDisplayDelegate!!

    override fun displayError(error: String) {
        displayErrorMessage(Error.SnackbarMessage(error))
    }

    override fun displayErrorMessage(error: Error<*>) {
        errorDisplayDelegate!!.displayError(error)
    }
}

