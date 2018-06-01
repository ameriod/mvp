package me.ameriod.lib.mvp.app.people;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.ameriod.lib.mvp.app.R;
import me.ameriod.lib.mvp.app.models.Person;

public class PeopleViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.people_item_tv_name);
    }

    public void setup(@NonNull Person person) {
        tvName.setText(person.getName());
    }
}
