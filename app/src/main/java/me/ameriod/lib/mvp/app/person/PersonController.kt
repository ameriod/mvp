package me.ameriod.lib.mvp.app.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.person_controller.view.*
import me.ameriod.lib.mvp.app.BaseControllerMvp
import me.ameriod.lib.mvp.app.R
import me.ameriod.lib.mvp.app.models.Person

class PersonController(args: Bundle?) : BaseControllerMvp<PersonContract.View, PersonContract.Presenter>(args), PersonContract.View {

    private var mId: String? = null

    init {
        if (args != null) {
            mId = args.getString(EXTRA_ID)
        }
        if (mId.isNullOrEmpty()) {
            throw IllegalArgumentException("Error an is required")
        }
    }

    override fun setPerson(person: Person) {
        view!!.person_tv_name.text = person.name
        view!!.person_tv_species.text = person.getSpecies()
        view!!.person_tv_birth_year.text = person.birthYear
        view!!.person_tv_eye_color.text = person.eyeColor
        view!!.person_tv_films.text = person.getFilms()
        view!!.person_tv_gender.text = person.gender
        view!!.person_tv_hair_color.text = person.hairColor
        view!!.person_tv_skin_color.text = person.skinColor
        view!!.person_tv_height.text = person.height
        view!!.person_tv_home_world.text = person.homeworld
        view!!.person_tv_mass.text = person.mass
        view!!.person_tv_star_ships.text = person.getStarShips()
        view!!.person_tv_vehicles.text = person.getVehicles()
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.person_controller, container, false)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        getPresenter().getPerson()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            view!!.person_loading_view.show()
        } else {
            view!!.person_loading_view.hide()
        }
    }

    override fun displayError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

    override fun createPresenter(): PersonContract.Presenter {
        return PersonPresenter(applicationContext!!, mId!!)
    }

    companion object {

        private const val EXTRA_ID = "extra_id"

        fun newInstance(id: String): PersonController {
            val args = Bundle()
            args.putString(EXTRA_ID, id)
            return PersonController(args)
        }
    }
}
