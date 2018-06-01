package me.ameriod.lib.mvp.app.people;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.ameriod.lib.mvp.app.R;
import me.ameriod.lib.mvp.app.models.Person;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> implements View.OnClickListener {

    private final LayoutInflater inflater;
    private List<Person> items;
    private OnPersonClickedListener listener;

    public PeopleAdapter(@NonNull Context context, @NonNull OnPersonClickedListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PeopleViewHolder viewHolder = new PeopleViewHolder(inflater.inflate(R.layout.people_item, parent, false));
        viewHolder.itemView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        Person person = items.get(position);
        holder.setup(person);
        holder.itemView.setTag(person);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<Person> newItems) {
        if (items == null) {
            this.items = new ArrayList<>(newItems);
            notifyDataSetChanged();
        } else {
            final List<Person> oldItems = new ArrayList<>(items);
            this.items = new ArrayList<>(newItems);
            DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return oldItems.size();
                }

                @Override
                public int getNewListSize() {
                    return items.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return oldItems.get(oldItemPosition).equals(items.get(newItemPosition));
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return oldItems.get(oldItemPosition).equals(items.get(newItemPosition));
                }
            }).dispatchUpdatesTo(this);
        }

    }

    @Override
    public void onClick(View view) {
        listener.onPersonClicked(view, (Person) view.getTag());
    }

    public interface OnPersonClickedListener {
        void onPersonClicked(@NonNull View view, Person person);
    }
}
