package me.ameriod.lib.mvp.app.people;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import java.util.List;

import butterknife.BindView;
import me.ameriod.lib.mvp.app.BaseControllerMvp;
import me.ameriod.lib.mvp.app.R;
import me.ameriod.lib.mvp.app.RecyclerViewEndlessScrollListener;
import me.ameriod.lib.mvp.app.models.Person;
import me.ameriod.lib.mvp.app.person.PersonController;

public class PeopleController extends BaseControllerMvp<PeopleContract.View, PeopleContract.Presenter>
        implements PeopleContract.View, PeopleAdapter.OnPersonClickedListener,
        RecyclerViewEndlessScrollListener.OnScrollToEndListener {

    @BindView(R.id.people_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.people_loading_view)
    View loading;

    private PeopleAdapter adapter;

    public static PeopleController newInstance() {
        return new PeopleController();
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.people_controller, container, false);
    }

    @Override
    protected void onPostCreateView(@NonNull View view, @NonNull ViewGroup container) {
        super.onPostCreateView(view, container);
        Context context = view.getContext();
        adapter = new PeopleAdapter(context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerViewEndlessScrollListener(linearLayoutManager, this));
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        getPresenter().getPeople();
    }

    @Override
    public void setPeople(List<Person> people) {
        adapter.setItems(people);
    }

    @Override
    public void showProgress(boolean show) {
        loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayError(@Nullable String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public PeopleContract.Presenter createPresenter() {
        return PeoplePresenter.newInstance(getApplicationContext());
    }

    @Override
    public void onPersonClicked(@NonNull View view, Person person) {
        getRouter().pushController(RouterTransaction.with(PersonController.newInstance(person.getId()))
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void onScrolledToEnd() {
        getPresenter().getMorePeople();
    }
}
