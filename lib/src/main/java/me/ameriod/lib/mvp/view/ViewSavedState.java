package me.ameriod.lib.mvp.view;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

class ViewSavedState extends View.BaseSavedState {

    public static final Parcelable.Creator<ViewSavedState> CREATOR =
            new Parcelable.Creator<ViewSavedState>() {
                public ViewSavedState createFromParcel(Parcel in) {
                    return new ViewSavedState(in);
                }

                public ViewSavedState[] newArray(int size) {
                    return new ViewSavedState[size];
                }
            };

    private Bundle state;

    public ViewSavedState(Parcelable superState) {
        super(superState);
    }

    private ViewSavedState(Parcel in) {
        super(in);
        this.state = in.readBundle(getClass().getClassLoader());
    }

    public Bundle getState() {
        return state;
    }

    public void setState(Bundle state) {
        this.state = state;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeBundle(this.state);
    }
}