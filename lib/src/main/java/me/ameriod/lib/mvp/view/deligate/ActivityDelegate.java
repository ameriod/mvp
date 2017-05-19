package me.ameriod.lib.mvp.view.deligate;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.ameriod.lib.mvp.Mvp;

public interface ActivityDelegate<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    void onCreate(@Nullable Bundle savedInstanceState);

    void onSaveInstanceState(@Nullable Bundle outState);

    void onDestroy();

    P getPresenter();
}
