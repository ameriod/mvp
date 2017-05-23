package me.ameriod.lib.mvp.view

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class ViewSavedState : View.BaseSavedState {

    var state: Bundle? = null

    constructor(superState: Parcelable) : super(superState)

    private constructor(`in`: Parcel) : super(`in`) {
        this.state = `in`.readBundle(javaClass.classLoader)
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeBundle(this.state)
    }

    companion object {

        val CREATOR: Parcelable.Creator<ViewSavedState> = object : Parcelable.Creator<ViewSavedState> {
            override fun createFromParcel(`in`: Parcel): ViewSavedState {
                return ViewSavedState(`in`)
            }

            override fun newArray(size: Int): Array<ViewSavedState?> {
                return arrayOfNulls(size)
            }
        }
    }
}