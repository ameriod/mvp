package me.ameriod.lib.error

import android.view.View

class ErrorDisplayDelegateImpl {

    private var view: View? = null
    private var error: Error<in Any>? = null

    @Suppress("UNCHECKED_CAST")
    fun displayError(newError: Error<*>) {
        error?.remove(error)
        error = view?.let {
            (newError as Error<Any>)
                    .apply { show(it) }
        }
    }

    fun displaySnackBar(message: CharSequence) {
        displayError(Error.SnackbarMessage(message))
    }

    fun displayToast(message: CharSequence) {
        displayError(Error.ToastMessage(message))
    }

    fun attachView(view: View) {
        this.view = view
    }

    fun deatchView() {
        error?.remove(error)
        error = null
        view = null
    }
}