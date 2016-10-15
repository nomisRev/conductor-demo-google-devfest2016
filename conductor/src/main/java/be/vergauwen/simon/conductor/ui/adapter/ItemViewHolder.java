package be.vergauwen.simon.conductor.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import be.vergauwen.simon.common.di.model.Item;
import be.vergauwen.simon.common.ui.widget.ItemIconView;
import be.vergauwen.simon.conductor.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.action_icon)
    ItemIconView itemIconView;
    @BindView(R.id.action_text)
    TextView actionText;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Item item) {
        actionText.setText(item.getName());
        itemIconView.setIcon(item.getIcon(itemView.getContext()));
        itemIconView.setColorId(item.getItemColorId());
    }
}
