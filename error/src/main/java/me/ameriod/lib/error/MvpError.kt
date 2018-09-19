package me.ameriod.lib.error

import android.view.View

class MvpError<T : Any>(val error: Error<T>,
               private var display: T? = null) {

    fun show(view: View) {
        display = error.show(view)
    }

    fun remove() {
        error.remove(display)
    }
}