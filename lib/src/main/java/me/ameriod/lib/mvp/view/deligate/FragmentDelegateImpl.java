package me.ameriod.lib.mvp.view.deligate;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.ameriod.lib.mvp.Mvp;

public class FragmentDelegateImpl<V extends Mvp.View, P extends Mvp.Presenter<V>> implements FragmentDelegate<V, P> {

    private FragmentDelegateCallback<V, P> delegateCallback;
    private P presenter;

    public FragmentDelegateImpl(FragmentDelegateCallback<V, P> delegateCallback) {
        this.delegateCallback = delegateCallback;
    }

    @Override
    public void onViewCreated(@Nullable Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = delegateCallback.createPresenter();
        }
        presenter.attachView(delegateCallback.getMvpView());
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
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
