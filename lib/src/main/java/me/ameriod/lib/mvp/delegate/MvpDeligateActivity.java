package me.ameriod.lib.mvp.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.ameriod.lib.mvp.Mvp;

public interface MvpDeligateActivity<V extends Mvp.View, P extends Mvp.Presenter<V>> {

    void onCreate(@Nullable Bundle savedInstanceState);

    void onSaveInstanceState(@NonNull Bundle outState);

    void onDestroy();
}
