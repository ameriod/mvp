package me.ameriod.lib.mvp.delegate;

import android.os.Parcelable;

import me.ameriod.lib.mvp.Mvp;

public interface MvpDeligateViewGroup<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    void onAttachedToWindow();

    void onDetachedFromWindow();

    Parcelable onSaveInstanceState();

    void onRestoreInstanceState(Parcelable state);
}
