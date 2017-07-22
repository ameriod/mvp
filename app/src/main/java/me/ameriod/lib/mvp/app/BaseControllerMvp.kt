package me.ameriod.lib.mvp.app

import android.os.Bundle
import android.view.View
import android.view.ViewGroup

import butterknife.ButterKnife
import butterknife.Unbinder
import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.view.MvpController


abstract class BaseControllerMvp<V : Mvp.View, P : Mvp.Presenter<V>> : MvpController<V, P> {

    private var unbinder: Unbinder? = null

    constructor()

    constructor(args: Bundle?) : super(args)

    override fun onPostCreateView(view: View, container: ViewGroup) {
        super.onPostCreateView(view, container)
        unbinder = ButterKnife.bind(this, view)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        unbinder!!.unbind()
        unbinder = null
    }
}
