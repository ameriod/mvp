package me.ameriod.lib.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bluelinelabs.conductor.Controller;

import me.ameriod.lib.mvp.Mvp;

public abstract class MvpController<V extends Mvp.View, P extends Mvp.Presenter<V>> extends Controller
        implements Mvp.View {

    private P presenter;

    public MvpController() {
        createPresenter();
    }

    public MvpController(@Nullable Bundle args) {
        super(args);
        createPresenter();
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);
        presenter.restoreState(savedViewState);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.attachView((V) this);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        presenter.detachView();
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
        presenter.saveState(outState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    @NonNull
    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }
}
