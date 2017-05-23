package me.ameriod.lib.mvp.deligate

import me.ameriod.lib.mvp.Mvp

interface ActivityDelegateCallback<out V : Mvp.View, out P : Mvp.Presenter<out V>> : MvpDelegateCallback<V, P>
