package me.ameriod.lib.mvp.view.deligate;

import android.os.Parcelable;

import me.ameriod.lib.mvp.Mvp;

public interface ViewGroupMvpDelegateCallback<V extends Mvp.View, P extends Mvp.Presenter<V>>
        extends MvpDelegateCallback<V, P> {

    Parcelable superOnSaveInstanceState();

    void superOnRestoreInstanceState(Parcelable state);

}
