package me.ameriod.lib.mvp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import me.ameriod.lib.mvp.Mvp;

public abstract class MvpFragment<V extends Mvp.View, P extends Mvp.Presenter<V>> extends Fragment
        implements Mvp.View {

    protected P presenter;

    @Override
    @SuppressWarnings("unchecked")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView((V) this);
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @NonNull
    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }
}
