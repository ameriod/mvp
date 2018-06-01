package me.ameriod.lib.mvp.app.people;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ameriod.lib.mvp.app.R;
import me.ameriod.lib.mvp.app.models.Person;

public class PeopleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.people_item_tv_name)
    TextView tvName;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setup(@NonNull Person person) {
        tvName.setText(person.getName());
    }
}
