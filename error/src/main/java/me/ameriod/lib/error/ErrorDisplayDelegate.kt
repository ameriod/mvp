package me.ameriod.lib.error

import android.view.View

interface ErrorDisplayDelegate {

    fun displayError(error: Error<*>)

    fun attachView(view: View)

    fun detachView()

}
