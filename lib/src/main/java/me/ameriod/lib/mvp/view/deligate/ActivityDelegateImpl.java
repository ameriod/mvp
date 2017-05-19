package me.ameriod.lib.mvp.view.deligate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.ameriod.lib.mvp.Mvp;

public class ActivityDelegateImpl <V extends Mvp.View, P extends Mvp.Presenter<V>> implements ActivityDelegate<V, P>{

    @NonNull
    private ActivityDelegateCallback<V, P> delegateCallback;
    private P presenter;

    public ActivityDelegateImpl(@NonNull ActivityDelegateCallback<V, P> delegateCallback) {
        this.delegateCallback = delegateCallback;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = delegateCallback.createPresenter();
        }
        presenter.attachView((V) this);
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            presenter.saveState(savedInstanceState);
        }
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
    }

    @Override
    public P getPresenter() {
        return presenter;
    }
}
