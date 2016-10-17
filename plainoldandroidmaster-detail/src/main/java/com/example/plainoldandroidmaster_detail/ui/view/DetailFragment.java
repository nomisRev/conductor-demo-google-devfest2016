package com.example.plainoldandroidmaster_detail.ui.view;


import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.plainoldandroidmaster_detail.MainActivity;
import com.example.plainoldandroidmaster_detail.R;
import com.example.plainoldandroidmaster_detail.core.mvp.MVPFragment;

import be.vergauwen.simon.common.ui.component.DaggerDetailComponent;
import be.vergauwen.simon.common.ui.component.DetailComponent;
import be.vergauwen.simon.common.ui.contract.DetailContract;
import be.vergauwen.simon.common.ui.presenter.DetailPresenter;
import be.vergauwen.simon.common.ui.widget.ItemIconView;
import butterknife.BindView;

public class DetailFragment extends MVPFragment<DetailContract.View, DetailPresenter, DetailComponent> implements DetailContract.View {

    public static final String FRAGMENT_ID = "detailFragment";
    private final static String KEY_TITLE = "DetailFragment.title";
    private final static String KEY_COLOR = "DetailFragment.color";
    private final static String KEY_DRAW_RES = "DetailFragment.drawableResId";
    private final static String KEY_POSITION = "DetailFragment.position";

    public static DetailFragment newInstance(@NonNull String title, @ColorRes int iconColorResId, @DrawableRes int drawableResId/*, int position*/) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putInt(KEY_COLOR, iconColorResId);
        args.putInt(KEY_DRAW_RES, drawableResId);
        //args.putInt(KEY_POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.background)
    LinearLayout background;
    @BindView(R.id.action_icon)
    ItemIconView itemIconView;
    @BindView(R.id.action_text)
    TextView actionText;

    @Override
    protected DetailComponent createComponent() {
        return DaggerDetailComponent.builder().activityComponent(((MainActivity) getActivity()).component).build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            itemIconView.setTransitionName(getString(R.string.reaveal_element) + getArguments().getInt(KEY_POSITION));
        }*/
        itemIconView.setColorId(getArguments().getInt(KEY_COLOR));
        itemIconView.setIcon(ContextCompat.getDrawable(getActivity(), getArguments().getInt(KEY_DRAW_RES)));
        actionText.setText(getArguments().getString(KEY_TITLE));


//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            int centerX = (itemIconView.getLeft() + itemIconView.getRight()) / 2;
//            int centerY = (itemIconView.getTop() + itemIconView.getBottom()) / 2;
//            int endRadius = Math.max(background.getWidth(), background.getHeight());
//            Animator anim = ViewAnimationUtils.createCircularReveal(background, centerX, centerY, 0, endRadius);
//            anim.setDuration(600);
//            anim.setInterpolator(new FastOutLinearInInterpolator());
//            anim.setStartDelay(700);
//            anim.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    background.setBackgroundColor(ContextCompat.getColor(getActivity(), getArguments().getInt(KEY_COLOR)));
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    background.setVisibility(View.VISIBLE);
//                }
//            });
//            anim.start();
//        }
    }
}
