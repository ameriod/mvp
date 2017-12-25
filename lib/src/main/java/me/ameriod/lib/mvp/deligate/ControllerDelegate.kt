package me.ameriod.lib.mvp.deligate

import android.os.Bundle

import me.ameriod.lib.mvp.Mvp

interface ControllerDelegate<in V : Mvp.View, out P : Mvp.Presenter<in V>> {

    fun onCreateView()

    fun onRestoreViewState(savedViewState: Bundle)

    fun onSaveViewState(outState: Bundle)

    fun onDestroyView()

    fun getPresenter(): P
}
