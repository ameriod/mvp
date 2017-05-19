package me.ameriod.lib.mvp.app.api;

import android.content.Context;
import android.support.annotation.NonNull;

import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.app.R;
import timber.log.Timber;

public class NetworkErrorHandler implements Mvp.ErrorHandler {

    @NonNull
    private Context context;

    public NetworkErrorHandler(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public String onError(Throwable e) {
        Timber.e(e, "Error");
        return context.getString(R.string.network_error);
    }
}
