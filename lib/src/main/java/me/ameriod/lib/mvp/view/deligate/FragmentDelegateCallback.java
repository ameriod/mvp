package me.ameriod.lib.mvp.view.deligate;

import me.ameriod.lib.mvp.Mvp;

public interface FragmentDelegateCallback<V extends Mvp.View, P extends Mvp.Presenter<V>>
        extends MvpDelegateCallback<V, P> {
}
