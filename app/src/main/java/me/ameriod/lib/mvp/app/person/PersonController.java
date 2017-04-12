package me.ameriod.lib.mvp.app.person;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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