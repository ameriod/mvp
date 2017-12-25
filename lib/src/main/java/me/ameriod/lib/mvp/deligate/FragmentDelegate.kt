package me.ameriod.lib.mvp.deligate

import android.os.Bundle

import me.ameriod.lib.mvp.Mvp

interface FragmentDelegate<in V : Mvp.View, out P : Mvp.Presenter<in V>> {

    fun onViewCreated(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onDestroyView()

    fun getPresenter(): P
}
