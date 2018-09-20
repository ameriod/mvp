package me.ameriod.lib.error

import android.view.View

class ErrorDisplayDelegateImpl : ErrorDisplayDelegate {

    private var view: View? = null
    private var currentError: Error<in Any>? = null
    private var currentDisplay: Any? = null

    @Suppress("UNCHECKED_CAST")
    override fun displayError(error: Error<*>) {
        currentError?.remove(currentError)
        view?.apply {
            currentError = error as Error<in Any>
            currentDisplay = currentError?.show(this)
        }
    }


    override fun attachView(view: View) {
        this.view = view
    }

    override fun detachView() {
        currentError?.remove(currentDisplay)
        currentError = null
        currentDisplay = null
        view = null
    }
}