package me.ameriod.lib.mvp.app.people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.ameriod.lib.mvp.app.R;
import me.ameriod.lib.mvp.app.RecyclerViewEndlessScrollListener;
import me.ameriod.lib.mvp.app.models.Person;
import me.ameriod.lib.mvp.app.person.PersonController;
import me.ameriod.lib.mvp.view.MvpController;

public class PeopleController extends MvpController<PeopleContract.View, PeopleContract.Presenter>
        implements PeopleContract.View, PeopleAdapter.OnPersonClickedListener,
        RecyclerViewEndlessScrollListener.OnScrollToEndListener {

    private RecyclerView recyclerView;
    private View loading;

    private PeopleAdapter adapter;

    public static PeopleController newInstance() {
        return new PeopleController();
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View v = inflater.inflate(R.layout.people_controller, container, false);
        recyclerView = v.findViewById(R.id.people_recycler_view);
        loading = v.findViewById(R.id.people_loading_view);
        return v;
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

    @NonNull
    @Override
    public PeopleContract.Presenter createPresenter() {
        return PeoplePresenter.newInstance(getApplicationContext());
    }

    @Override
    public void onPersonClicked(@NonNull View view, Person person) {
        getRouter().pushController(RouterTransaction.with(PersonController.Companion.newInstance(person.getId()))
                .pushChangeHandler(new HorizontalChangeHandler())
                .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    public void onScrolledToEnd() {
        getPresenter().getMorePeople();
    }
}
