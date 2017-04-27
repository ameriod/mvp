package me.ameriod.lib.mvp.view.deligate;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.view.ViewSavedState;

public class ViewGroupDelegateImpl<V extends Mvp.View, P extends Mvp.Presenter<V>> implements ViewMvpDelegate<V, P> {

    @Nullable
    private Bundle savedInstanceState;
    @NonNull
    private ViewGroupMvpDelegateCallback<V, P> callback;
    private P presenter;

    public ViewGroupDelegateImpl(@NonNull ViewGroupMvpDelegateCallback<V, P> callback) {
        this.callback = callback;
    }

    @Override
    public void onAttachedToWindow() {
        if (presenter == null) {
            presenter = callback.createPresenter();
        }
        presenter.attachView(callback.getMvpView());
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
        presenter.attachView(callback.getMvpView());
    }

    @Override
    public void onDetachedFromWindow() {
        presenter.detachView();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        ViewSavedState savedState = new ViewSavedState(callback.superOnSaveInstanceState());
        Bundle state = new Bundle();
        savedState.setState(state);
        presenter.saveState(state);
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof ViewSavedState)) {
            callback.superOnRestoreInstanceState(state);
            return;
        }
        ViewSavedState savedState = (ViewSavedState) state;
        callback.superOnRestoreInstanceState(savedState.getSuperState());
        savedInstanceState = savedState.getState();
    }

    @Override
    public P getPresenter() {
        return presenter;
    }
}

