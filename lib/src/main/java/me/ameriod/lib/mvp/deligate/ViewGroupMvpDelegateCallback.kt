package me.ameriod.lib.mvp.deligate

import android.os.Parcelable

import me.ameriod.lib.mvp.Mvp

interface ViewGroupMvpDelegateCallback<out V : Mvp.View, out P : Mvp.Presenter<out V>> : MvpDelegateCallback<V, P> {

    fun superOnSaveInstanceState(): Parcelable

    fun superOnRestoreInstanceState(state: Parcelable)
}
