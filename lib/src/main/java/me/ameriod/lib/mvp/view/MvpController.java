package me.ameriod.lib.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.view.deligate.ControllerDelegate;
import me.ameriod.lib.mvp.view.deligate.ControllerDelegateCallback;
import me.ameriod.lib.mvp.view.deligate.ControllerDelegateImpl;
import me.ameriod.lib.mvp.view.deligate.FragmentDelegate;

public abstract class MvpController<V extends Mvp.View, P extends Mvp.Presenter<V>> extends Controller
        implements Mvp.View, ControllerDelegateCallback<V, P> {

    protected P presenter;
    private ControllerDelegate<V, P> controllerDelegate;

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
        if (controllerDelegate == null) {
            controllerDelegate = new ControllerDelegateImpl<>(this);
        }
        controllerDelegate.onCreateView();
        onPostCreateView(view, container);
        return view;
    }

    protected void onPostCreateView(@NonNull View view, @NonNull ViewGroup container) {
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);
        controllerDelegate.onRestoreViewState(savedViewState);
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
        controllerDelegate.onSaveViewState(outState);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        controllerDelegate.onDestroyView();
    }

    @NonNull
    public abstract P createPresenter();

    public P getPresenter() {
        return controllerDelegate.getPresenter();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getMvpView() {
        return (V) this;
    }
}
