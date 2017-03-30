package me.ameriod.lib.mvp.presenter.rx;

import android.support.annotation.Nullable;
import android.util.Log;

public final class RxEspresso {
    private static final String TAG = "RxEspresso";

    private static RxEspresso INSTANCE;
    private int countingIdlingResource;
    @Nullable
    private Hook hook;
    private boolean enableLogging;

    private RxEspresso() {

    }

    public static RxEspresso getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RxEspresso();
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
