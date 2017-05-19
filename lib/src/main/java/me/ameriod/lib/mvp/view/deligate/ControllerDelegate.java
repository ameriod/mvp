package me.ameriod.lib.mvp.view.deligate;

import android.os.Bundle;
import android.support.annotation.NonNull;

import me.ameriod.lib.mvp.Mvp;

public interface ControllerDelegate<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    void onCreateView();

    void onRestoreViewState(@NonNull Bundle savedViewState);

    void onSaveViewState(@NonNull Bundle outState);

    void onDestroyView();

    P getPresenter();
}
