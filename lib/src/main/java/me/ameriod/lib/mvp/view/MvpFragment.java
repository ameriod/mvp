package me.ameriod.lib.mvp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.view.deligate.FragmentDelegate;
import me.ameriod.lib.mvp.view.deligate.FragmentDelegateCallback;
import me.ameriod.lib.mvp.view.deligate.FragmentDelegateImpl;

public abstract class MvpFragment<V extends Mvp.View, P extends Mvp.Presenter<V>> extends Fragment
        implements Mvp.View, FragmentDelegateCallback<V, P> {

    private FragmentDelegate<V, P> fragmentDelegate;

    @Override
    @SuppressWarnings("unchecked")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (fragmentDelegate == null) {
            fragmentDelegate = new FragmentDelegateImpl<>(this);
        }
        fragmentDelegate.onViewCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        fragmentDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentDelegate.onDestroyView();
    }

    @NonNull
    @Override
    public abstract P createPresenter();

    @Override
    public P getPresenter() {
        return fragmentDelegate.getPresenter();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getMvpView() {
        return (V) this;
    }
}
