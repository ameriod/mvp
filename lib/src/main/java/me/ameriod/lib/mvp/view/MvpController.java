package me.ameriod.lib.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import me.ameriod.lib.mvp.Mvp;

public abstract class MvpController<V extends Mvp.View, P extends Mvp.Presenter<V>> extends Controller
        implements Mvp.View {

    private P presenter;

    public MvpController() {
    }

    public MvpController(@Nullable Bundle args) {
        super(args);
    }

    @NonNull
    protected abstract View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        presenter = createPresenter();
        presenter.attachView((V) this);
        onPostCreateView(view, container);
        return view;
    }

    protected void onPostCreateView(@NonNull View view, @NonNull ViewGroup container) {
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);
        presenter.restoreState(savedViewState);
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
        presenter.saveState(outState);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        presenter.detachView();
    }

    @NonNull
    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }
}
