package me.ameriod.lib.mvp.view.deligate;

import me.ameriod.lib.mvp.Mvp;

public interface ControllerDelegateCallback<V extends Mvp.View, P extends Mvp.Presenter<V>>
        extends MvpDelegateCallback<V, P> {
}
