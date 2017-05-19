package me.ameriod.lib.mvp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.view.MvpController;


public abstract class BaseControllerMvp<V extends Mvp.View, P extends Mvp.Presenter<V>> extends MvpController<V, P> {

    private Unbinder unbinder;

    public BaseControllerMvp() {
    }

    public BaseControllerMvp(@Nullable Bundle args) {
        super(args);
    }

    @Override
    protected void onPostCreateView(@NonNull View view, @NonNull ViewGroup container) {
        super.onPostCreateView(view, container);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        unbinder.unbind();
        unbinder = null;
    }
}
