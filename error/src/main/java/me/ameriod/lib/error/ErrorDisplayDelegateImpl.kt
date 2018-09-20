package me.ameriod.lib.error

import android.view.View

class ErrorDisplayDelegateImpl : ErrorDisplayDelegate {

    private var view: View? = null
    private var currentError: Error<in Any>? = null

    @Suppress("UNCHECKED_CAST")
    override fun displayError(error: Error<*>) {
        currentError?.remove(currentError)
        currentError = view?.let {
            (error as Error<Any>)
                    .apply { show(it) }
        }
    }


    override fun attachView(view: View) {
        this.view = view
    }

    override fun detachView() {
        currentError?.remove(currentError)
        currentError = null
        view = null
    }
}