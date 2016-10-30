package me.ameriod.lib.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import me.ameriod.lib.mvp.Mvp;

public abstract class MvpAppCompatActivity<V extends Mvp.View, P extends Mvp.Presenter<V>> extends AppCompatActivity
        implements Mvp.View {

    private P presenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView((V) this);
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @NonNull
    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }
}

