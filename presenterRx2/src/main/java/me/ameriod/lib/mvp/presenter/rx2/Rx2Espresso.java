package me.ameriod.lib.mvp.presenter.rx2;

import android.support.annotation.Nullable;
import android.util.Log;

public final class Rx2Espresso {
    private static final String TAG = "Rx2Espresso";

    private static Rx2Espresso INSTANCE;
    private int countingIdlingResource;
    @Nullable
    private Hook hook;
    private boolean enableLogging;

    private Rx2Espresso() {

    }

    public static Rx2Espresso getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Rx2Espresso();
        }
        return INSTANCE;
    }

    public void increment() {
        countingIdlingResource++;
        if (enableLogging) {
            Log.i(TAG, "incremented - count: " + countingIdlingResource);
        }
        if (hook != null) {
            hook.incremented();
        }
    }

    public void decrement() {
        countingIdlingResource--;
        if (enableLogging) {
            Log.i(TAG, "decremented - count: " + countingIdlingResource);
        }
        if (hook != null) {
            hook.decremented();
        }
    }

    public void setHook(@Nullable Hook hook) {
        if (this.hook != null && hook != null) {
            throw new IllegalStateException("Error need to null out the hook before the setting");
        }
        this.hook = hook;
    }

    public void enableLogging(boolean enable) {
        this.enableLogging = enable;
    }

    public interface Hook {

        void incremented();

        void decremented();
    }
}
