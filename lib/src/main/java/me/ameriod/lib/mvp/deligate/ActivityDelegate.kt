package me.ameriod.lib.mvp.deligate

import android.os.Bundle

import me.ameriod.lib.mvp.Mvp

interface ActivityDelegate<in V : Mvp.View, out P : Mvp.Presenter<in V>> {

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle?)

    fun onDestroy()

    fun getPresenter(): P
}
