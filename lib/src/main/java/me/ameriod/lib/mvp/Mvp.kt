package me.ameriod.lib.mvp

import android.os.Bundle

/**
 * The base interfaces for the Presenter and the View.
 */
interface Mvp {

    /**
     * The presenter interface

     * @param <T> of a [Mvp.View]
    </T> */
    interface Presenter<T : Mvp.View> {

        /**
         * Binds the view to the presenter

         * @param view for the presenter
         */
        fun attachView(view: T)

        /**
         * Restores the state from the android component

         * @param savedState from the android component
         */
        fun restoreState(savedState: Bundle)

        /**
         * Saves the sate of the presenter to the android component

         * @param outState to save the state from the android component
         */
        fun saveState(outState: Bundle)

        /**
         * Unbinds the view from the presenter
         */
        fun detachView()

        fun getView(): T
    }

    /**
     * The View interface
     */
    interface View {

        fun showProgress(show: Boolean)

        fun displayError(error: String)
    }

    /**
     * The interface to handle error messages
     */
    interface ErrorHandler {
        fun onError(e: Throwable): String
    }
}
