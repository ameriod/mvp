package me.ameriod.lib.mvp.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.ameriod.lib.mvp.Mvp;

public interface MvpDeligateFragment<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    void onSaveInstanceState(Bundle outState);

    void onDestroyView();
}
