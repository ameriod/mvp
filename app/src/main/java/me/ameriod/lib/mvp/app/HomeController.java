package me.ameriod.lib.mvp.app;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.ameriod.lib.mvp.app.people.PeopleController;

public class HomeController extends Controller {

    private Unbinder unbinder;

    public static HomeController newInstance() {
        return new HomeController();
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View v = inflater.inflate(R.layout.home_controller, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        unbinder.unbind();
        unbinder = null;
    }

    @OnClick(R.id.home_btn_people)
    public void onClickPeople() {
        getRouter().pushController(RouterTransaction.with(PeopleController.newInstance())
                .popChangeHandler(new HorizontalChangeHandler())
                .pushChangeHandler(new HorizontalChangeHandler()));
    }
}
