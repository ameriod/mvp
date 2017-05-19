package me.ameriod.lib.mvp.view.deligate;

import android.os.Bundle;
import android.support.annotation.NonNull;

import me.ameriod.lib.mvp.Mvp;

public class ControllerDelegateImpl<V extends Mvp.View, P extends Mvp.Presenter<V>> implements ControllerDelegate<V, P>{

    private ControllerDelegateCallback<V, P> delegateCallback;
    private P presenter;

    public ControllerDelegateImpl(ControllerDelegateCallback<V, P> delegateCallback) {
        this.delegateCallback = delegateCallback;
    }

    @Override
    public void onCreateView() {
        if (presenter == null) {
            presenter = delegateCallback.createPresenter();
        }
        presenter.attachView(delegateCallback.getMvpView());
    }

    @Override
    public void onRestoreViewState(@NonNull Bundle savedViewState) {
        presenter.restoreState(savedViewState);
    }

    @Override
    public void onSaveViewState(@NonNull Bundle outState) {
        presenter.saveState(outState);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
    }

    @Override
    public P getPresenter() {
        return presenter;
    }
}
