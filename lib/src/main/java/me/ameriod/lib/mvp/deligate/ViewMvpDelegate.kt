package me.ameriod.lib.mvp.deligate

import android.os.Parcelable

import me.ameriod.lib.mvp.Mvp

interface ViewMvpDelegate<in V : Mvp.View, out P : Mvp.Presenter<in V>> {

    fun onAttachedToWindow()

    fun onDetachedFromWindow()

    fun onRestoreInstanceState(state: Parcelable)

    fun onSaveInstanceState(): Parcelable

    fun getPresenter(): P
}
