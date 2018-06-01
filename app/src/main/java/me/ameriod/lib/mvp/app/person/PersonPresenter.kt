package me.ameriod.lib.mvp.app.person

import android.content.Context
import android.os.Bundle
import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.app.api.NetworkErrorHandler
import me.ameriod.lib.mvp.app.models.Person
import me.ameriod.lib.mvp.presenter.rx2.BasePresenterRx2
import me.ameriod.lib.mvp.presenter.rx2.IObservableSchedulerRx2

class PersonPresenter(scheduler: IObservableSchedulerRx2, errorHandler: Mvp.ErrorHandler, private val interactor: PersonContract.Interactor) :
        BasePresenterRx2<PersonContract.View>(scheduler, errorHandler), PersonContract.Presenter {

    constructor(context: Context, id: String) : this(IObservableSchedulerRx2.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN,
            NetworkErrorHandler(context), PersonInteractor(id))

    internal var person: Person? = null

    override fun saveState(outState: Bundle) {
        super.saveState(outState)
    }

    override fun restoreState(savedState: Bundle) {
        super.restoreState(savedState)
    }

    override fun getPerson() {
        if (person == null) {
            getView().showProgress(true)
            addDisposable(interactor.getPerson().
                    compose(scheduler.schedule<Person>()).
                    subscribe({ personInternal: Person ->
                        person = personInternal
                        getView().setPerson(personInternal)
                        getView().showProgress(false)
                    }) { t: Throwable? ->
                        getView().displayError(errorHandler.onError(t!!))
                        getView().showProgress(false)
                    })
        } else {
            getView().setPerson(person!!)
        }

    }
}

