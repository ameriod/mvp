package me.ameriod.lib.mvp.deligate

import me.ameriod.lib.mvp.Mvp

interface MvpDelegateCallback<out V : Mvp.View, out P : Mvp.Presenter<out V>> {

    fun createPresenter(): P

    fun getPresenter(): P

    fun getMvpView(): V
}