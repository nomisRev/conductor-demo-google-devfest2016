package com.example.plainoldandroidmaster_detail.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.plainoldandroidmaster_detail.R;

import be.vergauwen.simon.common.di.model.Item;
import be.vergauwen.simon.common.ui.widget.ItemIconView;
import butterknife.BindView;
import butterknife.ButterKnife;

class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.action_icon)
    ItemIconView itemIconView;
    @BindView(R.id.action_text)
    TextView actionText;

    ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(Item item) {
        actionText.setText(item.getName());
        itemIconView.setIcon(item.getIcon(itemView.getContext()));
        itemIconView.setColorId(item.getItemColorId());
    }
}