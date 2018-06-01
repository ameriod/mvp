package me.ameriod.lib.mvp.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import androidx.annotation.NonNull;
import me.ameriod.lib.mvp.app.people.PeopleController;

public class HomeController extends Controller implements View.OnClickListener {


    public static HomeController newInstance() {
        return new HomeController();
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View v = inflater.inflate(R.layout.home_controller, container, false);
        v.findViewById(R.id.home_btn_people).setOnClickListener(this);
        return v;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        if (vId == R.id.home_btn_people) {
            getRouter().pushController(RouterTransaction.with(PeopleController.newInstance())
                    .popChangeHandler(new HorizontalChangeHandler())
                    .pushChangeHandler(new HorizontalChangeHandler()));
        }
    }
}
