package me.ameriod.lib.mvp.view.deligate;

import android.os.Parcelable;

import me.ameriod.lib.mvp.Mvp;

public interface ViewMvpDelegate<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    void onAttachedToWindow();

    void onDetachedFromWindow();

    void onRestoreInstanceState(Parcelable state);

    Parcelable onSaveInstanceState();

    P getPresenter();
}
