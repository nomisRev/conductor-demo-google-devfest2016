package be.vergauwen.simon.conductor.ui.adapter;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import be.vergauwen.simon.common.di.model.Item;
import be.vergauwen.simon.conductor.R;
import kotlin.collections.CollectionsKt;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private final List<Item> items = new ArrayList<>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.bind(items.get(position));
    }

    public void addItem(@NonNull final Item item) {
        if (!items.contains(item)) {
            items.add(item);
            notifyItemInserted(items.indexOf(item));
        }
    }

    public @Nullable Item getItem(final int position) {
        return CollectionsKt.getOrNull(items,position);
    }
}
