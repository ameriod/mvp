package me.ameriod.lib.mvp.view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import me.ameriod.lib.error.Error
import me.ameriod.lib.error.ErrorDisplayDelegate
import me.ameriod.lib.error.ErrorDisplayDelegateImpl

import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.deligate.ViewGroupDelegateImpl
import me.ameriod.lib.mvp.deligate.ViewGroupMvpDelegateCallback
import me.ameriod.lib.mvp.deligate.ViewMvpDelegate

abstract class MvpLinearLayout<V : Mvp.View, P : Mvp.Presenter<V>> : LinearLayout, Mvp.View,
        ViewGroupMvpDelegateCallback<V, P> {

    private var delegate: ViewMvpDelegate<V, P>? = null
    private var errorDisplayDelegate : ErrorDisplayDelegate? = null

    constructor(context: Context) : super(context) {
        this.init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.init(context)
    }

    @TargetApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        this.init(context)
    }

    protected abstract fun init(context: Context)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (delegate == null) {
            delegate = ViewGroupDelegateImpl(this)
        }
        delegate!!.onAttachedToWindow()

        if (errorDisplayDelegate == null) {
            errorDisplayDelegate = ErrorDisplayDelegateImpl()
        }
        errorDisplayDelegate!!.attachView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        delegate!!.onDetachedFromWindow()
        errorDisplayDelegate!!.detachView()
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(): Parcelable {
        return delegate!!.onSaveInstanceState()
    }

    @SuppressLint("MissingSuperCall")
    override fun onRestoreInstanceState(state: Parcelable) {
        delegate!!.onRestoreInstanceState(state)
    }

    abstract override fun createPresenter(): P

    override fun getPresenter(): P {
        return delegate!!.getPresenter()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getMvpView(): V {
        return this as V
    }

    override fun superOnSaveInstanceState(): Parcelable {
        return super.onSaveInstanceState()
    }

    override fun superOnRestoreInstanceState(state: Parcelable) {
        super.onRestoreInstanceState(state)
    }

    fun getErrorDisplayDelegate() : ErrorDisplayDelegate = errorDisplayDelegate!!

    override fun displayErrorMessage(error: Error<*>) {
        errorDisplayDelegate!!.displayError(error)
    }

    override fun displayError(error: String) {
        displayErrorMessage(Error.SnackbarMessage(error))
    }
}
