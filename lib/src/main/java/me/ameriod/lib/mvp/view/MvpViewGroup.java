package me.ameriod.lib.mvp.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import me.ameriod.lib.mvp.Mvp;

public abstract class MvpViewGroup<V extends Mvp.View, P extends Mvp.Presenter<V>> extends ViewGroup
        implements Mvp.View {

    private P presenter;

    @Nullable
    private Bundle savedInstanceState;

    public MvpViewGroup(Context context) {
        super(context);
        init(context);
    }

    public MvpViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MvpViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public MvpViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected abstract void init(@NonNull Context context);

    @Override
    @SuppressWarnings("unchecked")
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView((V) this);
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.detachView();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        ViewSavedState savedState = new ViewSavedState(super.onSaveInstanceState());
        Bundle state = new Bundle();
        savedState.setState(state);
        presenter.saveState(state);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof ViewSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        ViewSavedState savedState = (ViewSavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        savedInstanceState = savedState.getState();
    }

    @NonNull
    protected abstract P createPresenter();

    protected P getPresenter() {
        return presenter;
    }
}
