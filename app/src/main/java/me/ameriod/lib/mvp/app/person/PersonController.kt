package me.ameriod.lib.mvp.app.person

import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import me.ameriod.lib.mvp.app.BaseControllerMvp
import me.ameriod.lib.mvp.app.R
import me.ameriod.lib.mvp.app.models.Person

class PersonController(args: Bundle?) : BaseControllerMvp<PersonContract.View, PersonContract.Presenter>(args), PersonContract.View {

    @BindView(R.id.person_loading_view)
    internal var loading: ContentLoadingProgressBar? = null
    @BindView(R.id.person_tv_name)
    internal var tvName: TextView? = null
    @BindView(R.id.person_tv_species)
    internal var tvSpecies: TextView? = null
    @BindView(R.id.person_tv_mass)
    internal var tvMass: TextView? = null
    @BindView(R.id.person_tv_eye_color)
    internal var tvEyeColor: TextView? = null
    @BindView(R.id.person_tv_skin_color)
    internal var tvSkinColor: TextView? = null
    @BindView(R.id.person_tv_hair_color)
    internal var tvHairColor: TextView? = null
    @BindView(R.id.person_tv_birth_year)
    internal var tvBirthYear: TextView? = null
    @BindView(R.id.person_tv_height)
    internal var tvHeight: TextView? = null
    @BindView(R.id.person_tv_gender)
    internal var tvGender: TextView? = null
    @BindView(R.id.person_tv_home_world)
    internal var tvHomeWorld: TextView? = null
    @BindView(R.id.person_tv_films)
    internal var tvFilms: TextView? = null
    @BindView(R.id.person_tv_star_ships)
    internal var tvStarShips: TextView? = null
    @BindView(R.id.person_tv_vehicles)
    internal var tvVehicles: TextView? = null

    private var mId: String? = null

    init {
        if (args != null) {
            mId = args.getString(EXTRA_ID)
        }
        if (mId == null || mId!!.isEmpty()) {
            throw IllegalArgumentException("Error an is required")
        }
    }

    override fun setPerson(person: Person) {
        tvName!!.text = person.name()
        tvSpecies!!.text = person.species
        tvBirthYear!!.text = person.birthYear()
        tvEyeColor!!.text = person.eyeColor()
        tvFilms!!.text = person.films
        tvGender!!.text = person.gender()
        tvHairColor!!.text = person.hairColor()
        tvSkinColor!!.text = person.skinColor()
        tvHeight!!.text = person.height()
        tvHomeWorld!!.text = person.homeworld()
        tvMass!!.text = person.mass()
        tvStarShips!!.text = person.starShips
        tvVehicles!!.text = person.vehicles
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
            loading!!.show()
        } else {
            loading!!.hide()
        }
    }

    override fun displayError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

    override fun createPresenter(): PersonContract.Presenter {
        return PersonPresenter(applicationContext!!, mId!!)
    }

    companion object {

        private val EXTRA_ID = "extra_id"

        fun newInstance(id: String): PersonController {
            val args = Bundle()
            args.putString(EXTRA_ID, id)
            return PersonController(args)
        }
    }
}
