package me.ameriod.lib.mvp.view.deligate;

import android.support.annotation.NonNull;

import me.ameriod.lib.mvp.Mvp;

public interface MvpDelegateCallback<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    @NonNull
    P createPresenter();

    P getPresenter();

    V getMvpView();
}