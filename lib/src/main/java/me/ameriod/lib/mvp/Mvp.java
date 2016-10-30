package me.ameriod.lib.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * The base interfaces for the Presenter and the View.
 */
public class Mvp {

    /**
     * The presenter interface
     *
     * @param <T> of a {@link Mvp.View}
     */
    public interface Presenter<T extends Mvp.View> {

        /**
         * Binds the view to the presenter
         *
         * @param view for the presenter
         */
        void attachView(@NonNull T view);

        /**
         * Restores the state from the android component
         *
         * @param savedState from the android component
         */
        void restoreState(@NonNull Bundle savedState);

        /**
         * Saves the sate of the presenter to the android component
         *
         * @param outState to save the state from the android component
         */
        void saveState(@NonNull Bundle outState);

        /**
         * Unbinds the view from the presenter
         */
        void detachView();
    }

    /**
     * The View interface
     */
    public interface View {

        void showProgress(boolean show);

        void displayError(@Nullable String error);
    }
}
