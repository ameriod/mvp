package me.ameriod.lib.mvp.view.deligate;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.ameriod.lib.mvp.Mvp;

public interface FragmentDelegate<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    void onViewCreated(@Nullable Bundle savedInstanceState);

    void onSaveInstanceState(Bundle outState);

    void onDestroyView();

    P getPresenter();
}
