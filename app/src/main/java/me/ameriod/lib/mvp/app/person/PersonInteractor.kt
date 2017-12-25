package me.ameriod.lib.mvp.app.person

import io.reactivex.Observable
import me.ameriod.lib.mvp.app.api.SwapiClient
import me.ameriod.lib.mvp.app.models.Person

class PersonInteractor(private val client: SwapiClient, private val id: String) : PersonContract.Interactor {

    constructor(id: String) : this(SwapiClient(), id)

    override fun getPerson(): Observable<Person> {
        return client.getPerson(id)
    }
}