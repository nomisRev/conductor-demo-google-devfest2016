package com.example.plainoldandroidmaster_detail.ui.view;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.plainoldandroidmaster_detail.MainActivity;
import com.example.plainoldandroidmaster_detail.R;
import com.example.plainoldandroidmaster_detail.core.mvp.MVPFragment;
import com.example.plainoldandroidmaster_detail.ui.adapter.ItemAdapter;
import com.example.plainoldandroidmaster_detail.ui.adapter.ItemViewHolder;

import be.vergauwen.simon.common.di.model.Item;
import be.vergauwen.simon.common.di.modules.DataModule;
import be.vergauwen.simon.common.ui.component.DaggerMasterComponent;
import be.vergauwen.simon.common.ui.component.MasterComponent;
import be.vergauwen.simon.common.ui.contract.MasterContract;
import be.vergauwen.simon.common.ui.presenter.MasterPresenter;
import be.vergauwen.simon.common.ui.widget.util.OnItemClickListener;
import be.vergauwen.simon.common.ui.widget.util.RecyclerViewExtKt;
import butterknife.BindView;
import icepick.State;

public class MasterFragment extends MVPFragment<MasterContract.View, MasterPresenter, MasterComponent> implements MasterContract.View {

    public final static String FRAGMENT_TAG = "masterFragment";

    public static MasterFragment newInstance() {
        MasterFragment fragment = new MasterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @BindView(R.id.detailContainer)
    FrameLayout detailContainer;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @State
    int selectedIndex = 0;
    private boolean twoPaneView;
    private ItemAdapter itemAdapter;

    @Override
    protected MasterComponent createComponent() {
        return DaggerMasterComponent.builder()
                .activityComponent(((MainActivity) getActivity()).component)
                .dataModule(new DataModule())
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.master_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView();

        twoPaneView = (detailContainer != null);
        presenter.getData();
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
        RecyclerViewExtKt.addItemClickListener(recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull RecyclerView.ViewHolder view, int position) {
                onRowSelected(((ItemViewHolder) view).itemView, position);
            }
        });
    }

    void onRowSelected(final View view, int index) {
        selectedIndex = index;

        Item item = itemAdapter.getItem(index);
        if (item == null) return;

        DetailFragment detailFragment = DetailFragment.newInstance(item.getName(), item.getItemColorId(), item.getDrawableResId()/*, index*/);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName(getString(R.string.reaveal_element));
            detailFragment.setSharedElementEnterTransition(transition());
            detailFragment.setEnterTransition(new Fade());
            setExitTransition(new Fade());
            detailFragment.setReturnTransition(transition());
        }

        if (twoPaneView && detailContainer != null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailContainer, detailFragment, DetailFragment.FRAGMENT_ID)
                    .commit();
        } else {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addSharedElement(view, getString(R.string.reaveal_element) /*+ index*/)
                    .replace(R.id.container, detailFragment, DetailFragment.FRAGMENT_ID)
                    .addToBackStack(DetailFragment.FRAGMENT_ID)
                    .commit();
        }
    }

    @Override
    public void addItem(@NonNull Item item) {
        itemAdapter.addItem(item);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Transition transition() {
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setDuration(600);
        transitionSet.setInterpolator(PathInterpolatorCompat.create(0, 0.2f, 0, 1));
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMaximumAngle(90f);
        arcMotion.setMinimumHorizontalAngle(25f);
        arcMotion.setMinimumVerticalAngle(25f);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setPathMotion(arcMotion);
        transitionSet.addTransition(changeBounds);
        return transitionSet;
    }
}



