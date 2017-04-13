package me.ameriod.lib.mvp.app.person;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import me.ameriod.lib.mvp.app.BaseControllerMvp;
import me.ameriod.lib.mvp.app.R;
import me.ameriod.lib.mvp.app.models.Person;

public class PersonController extends BaseControllerMvp<PersonContract.View, PersonContract.Presenter> implements
        PersonContract.View {

    private static final String EXTRA_ID = "extra_id";

    @BindView(R.id.person_loading_view)
    ContentLoadingProgressBar loading;
    @BindView(R.id.person_tv_name)
    TextView tvName;
    @BindView(R.id.person_tv_species)
    TextView tvSpecies;
    @BindView(R.id.person_tv_mass)
    TextView tvMass;
    @BindView(R.id.person_tv_eye_color)
    TextView tvEyeColor;
    @BindView(R.id.person_tv_skin_color)
    TextView tvSkinColor;
    @BindView(R.id.person_tv_hair_color)
    TextView tvHairColor;
    @BindView(R.id.person_tv_birth_year)
    TextView tvBirthYear;
    @BindView(R.id.person_tv_height)
    TextView tvHeight;
    @BindView(R.id.person_tv_gender)
    TextView tvGender;
    @BindView(R.id.person_tv_home_world)
    TextView tvHomeWorld;
    @BindView(R.id.person_tv_films)
    TextView tvFilms;
    @BindView(R.id.person_tv_star_ships)
    TextView tvStarShips;
    @BindView(R.id.person_tv_vehicles)
    TextView tvVehicles;

    private String mId;

    public PersonController(@Nullable Bundle args) {
        super(args);
        if (args != null) {
            mId = args.getString(EXTRA_ID);
        }
        if (mId == null || mId.isEmpty()) {
            throw new IllegalArgumentException("Error an is required");
        }
    }

    public static PersonController newInstance(@NonNull String id) {
        Bundle args = new Bundle();
        args.putString(EXTRA_ID, id);
        return new PersonController(args);
    }

    @Override
    public void setPerson(@NonNull Person person) {
        tvName.setText(person.name());
        tvSpecies.setText(person.getSpecies());
        tvBirthYear.setText(person.birthYear());
        tvEyeColor.setText(person.eyeColor());
        tvFilms.setText(person.getFilms());
        tvGender.setText(person.gender());
        tvHairColor.setText(person.hairColor());
        tvSkinColor.setText(person.skinColor());
        tvHeight.setText(person.height());
        tvHomeWorld.setText(person.homeworld());
        tvMass.setText(person.mass());
        tvStarShips.setText(person.getStarShips());
        tvVehicles.setText(person.getVehicles());
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.person_controller, container, false);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        getPresenter().getPerson();
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            loading.show();
        } else {
            loading.hide();
        }
    }

    @Override
    public void displayError(@Nullable String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    protected PersonContract.Presenter createPresenter() {
        return PersonPresenter.newInstance(getApplicationContext(), mId);
    }
}
