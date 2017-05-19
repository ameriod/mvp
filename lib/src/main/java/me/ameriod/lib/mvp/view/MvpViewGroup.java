package me.ameriod.lib.mvp.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;

import me.ameriod.lib.mvp.Mvp;
import me.ameriod.lib.mvp.view.deligate.ViewGroupDelegateImpl;
import me.ameriod.lib.mvp.view.deligate.ViewGroupMvpDelegateCallback;
import me.ameriod.lib.mvp.view.deligate.ViewMvpDelegate;

public abstract class MvpViewGroup<V extends Mvp.View, P extends Mvp.Presenter<V>> extends ViewGroup
        implements Mvp.View, ViewGroupMvpDelegateCallback<V, P> {

    private ViewMvpDelegate<V, P> delegate;

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
        if (delegate == null) {
            delegate = new ViewGroupDelegateImpl<>(this);
        }
        delegate.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        delegate.onDetachedFromWindow();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected Parcelable onSaveInstanceState() {
        return delegate.onSaveInstanceState();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        delegate.onRestoreInstanceState(state);
    }

    @NonNull
    public abstract P createPresenter();

    @Override
    public P getPresenter() {
        return delegate.getPresenter();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public Parcelable superOnSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public void superOnRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }
}
