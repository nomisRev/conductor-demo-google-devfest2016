package be.vergauwen.simon.conductor.ui.controllers;


import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.vergauwen.simon.common.ui.component.DaggerDetailComponent;
import be.vergauwen.simon.common.ui.component.DetailComponent;
import be.vergauwen.simon.common.ui.contract.DetailContract;
import be.vergauwen.simon.common.ui.layout.LayoutInjector;
import be.vergauwen.simon.common.ui.presenter.DetailPresenter;
import be.vergauwen.simon.common.ui.widget.ItemIconView;
import be.vergauwen.simon.conductor.MainActivity;
import be.vergauwen.simon.conductor.core.mvp.MVPBaseController;

public class DetailViewController extends MVPBaseController<DetailContract.View, DetailPresenter, DetailComponent> implements DetailContract.View {

    private final static String KEY_TITLE = "DetailViewController.title";
    private final static String KEY_COLOR = "DetailViewController.color";
    private final static String KEY_DRAW_RES = "DetailViewController.drawableResId";

    LinearLayout background;
    ItemIconView itemIconView;
    TextView actionText;

    private LayoutInjector<DetailViewController> layoutInjector = new DetailViewLayout();

    public DetailViewController(Bundle args) {
        super(args);
    }

    public DetailViewController(@NonNull String title, @ColorRes int iconColorResId, @DrawableRes int drawableResId) {
        this(buildBundle(title, iconColorResId, drawableResId));
    }

    @Override
    protected DetailComponent createComponent() {
        return DaggerDetailComponent.builder().activityComponent(((MainActivity) getActivity()).component).build();
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return layoutInjector.injectLayout(this);
    }

    @Override
    protected void onViewCreated(@NonNull View view) {
        super.onViewCreated(view);
        background.setBackgroundColor(ContextCompat.getColor(getActivity(), getArgs().getInt(KEY_COLOR)));
        itemIconView.setColorId(getArgs().getInt(KEY_COLOR));
        itemIconView.setIcon(ContextCompat.getDrawable(getActivity(), getArgs().getInt(KEY_DRAW_RES)));
        actionText.setText(getArgs().getString(KEY_TITLE));
    }

    private static Bundle buildBundle(@NonNull String title, @ColorRes int iconColorResId, @DrawableRes int drawableResId) {
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putInt(KEY_COLOR, iconColorResId);
        args.putInt(KEY_DRAW_RES, drawableResId);
        return args;
    }
}