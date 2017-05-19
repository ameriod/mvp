package me.ameriod.lib.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.view.deligate.ActivityDelegate;
import me.ameriod.lib.mvp.view.deligate.ActivityDelegateCallback;
import me.ameriod.lib.mvp.view.deligate.ActivityDelegateImpl;
import me.ameriod.lib.mvp.view.deligate.FragmentDelegate;

public abstract class MvpAppCompatActivity<V extends Mvp.View, P extends Mvp.Presenter<V>> extends AppCompatActivity
        implements Mvp.View, ActivityDelegateCallback<V, P> {

    private ActivityDelegate<V, P> activityDelegate;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activityDelegate == null) {
            activityDelegate = new ActivityDelegateImpl<>(this);
        }
        activityDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        activityDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityDelegate.onDestroy();
    }

    @Override
    public P getPresenter() {
        return activityDelegate.getPresenter();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getMvpView() {
        return (V) this;
    }
}

