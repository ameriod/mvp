package me.ameriod.lib.mvp.app.person

import io.reactivex.Observable
import me.ameriod.lib.mvp.Mvp
import me.ameriod.lib.mvp.app.models.Person

class PersonContract {

    interface Interactor {

        fun getPerson(): Observable<Person>

    }

    interface Presenter : Mvp.Presenter<View> {

        fun getPerson()

    }

    interface View : Mvp.View {

        fun setPerson(person: Person)
    }
}
