package me.ameriod.lib.mvp.app

import android.os.Bundle
import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.view.MvpController


abstract class BaseControllerMvp<V : Mvp.View, P : Mvp.Presenter<V>> : MvpController<V, P> {

    constructor()

    constructor(args: Bundle?) : super(args)

}
