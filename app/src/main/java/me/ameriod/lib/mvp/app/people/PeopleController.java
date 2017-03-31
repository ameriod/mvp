package me.ameriod.lib.mvp.app.people;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.ameriod.lib.mvp.app.R;
import me.ameriod.lib.mvp.app.models.Person;
import me.ameriod.lib.mvp.view.MvpController;

public class PeopleController extends MvpController<PeopleContract.View, PeopleContract.Presenter>
        implements PeopleContract.View {

    public static PeopleController newInstance() {
        return new PeopleController();
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.people_controller, container, false);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        getPresenter().getPeople();
    }

    @Override
    public void setPeople(List<Person> people) {

    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void displayError(@Nullable String error) {

    }

    @NonNull
    @Override
    protected PeopleContract.Presenter createPresenter() {
        return PeoplePresenter.newInstance();
    }
}
