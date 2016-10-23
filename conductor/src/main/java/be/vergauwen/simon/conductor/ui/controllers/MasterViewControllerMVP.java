package be.vergauwen.simon.conductor.ui.controllers;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.RouterTransaction;

import org.jetbrains.annotations.NotNull;

import be.vergauwen.simon.common.di.changehandlers.CircularRevealChangeHandlerCompat;
import be.vergauwen.simon.common.di.model.Item;
import be.vergauwen.simon.common.di.modules.DataModule;
import be.vergauwen.simon.common.ui.component.DaggerMasterComponent;
import be.vergauwen.simon.common.ui.component.MasterComponent;
import be.vergauwen.simon.common.ui.contract.MasterContract;
import be.vergauwen.simon.common.ui.presenter.MasterPresenter;
import be.vergauwen.simon.common.ui.widget.util.OnItemClickListener;
import be.vergauwen.simon.common.ui.widget.util.RecyclerViewExtKt;
import be.vergauwen.simon.conductor.MainActivity;
import be.vergauwen.simon.conductor.R;
import be.vergauwen.simon.conductor.core.mvp.MVPBaseController;
import be.vergauwen.simon.conductor.ui.adapter.ItemAdapter;
import icepick.State;

public class MasterViewControllerMVP extends MVPBaseController<MasterContract.View, MasterPresenter, MasterComponent> implements MasterContract.View {

    @Override
    protected MasterComponent createComponent() {
        return DaggerMasterComponent.builder().activityComponent(((MainActivity) getActivity()).component).dataModule(new DataModule()).build();
    }

    LinearLayout container;
    RecyclerView recyclerView;
    ViewGroup detailContainer;

    @State
    int selectedIndex = 0;
    private boolean twoPaneView;
    private ItemAdapter itemAdapter;
    private LayoutBinder<MasterViewControllerMVP> viewBinder = new MasterViewLayout();

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return viewBinder.bind(this);
    }

    @Override
    protected void onViewCreated(@NonNull View view) {
        super.onViewCreated(view);
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
        RecyclerViewExtKt.addItemClickListener(recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull RecyclerView.ViewHolder view, int position) {
                onRowSelected(position);
            }
        });

        twoPaneView = (detailContainer != null);
        if (twoPaneView) {
            onRowSelected(selectedIndex);
        }
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        viewBinder.unbind(this);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.getData();
    }

    private void onRowSelected(int index) {
        Item item = itemAdapter.getItem(index);
        if (item != null) {
            showDetail(new DetailViewController(item.getName(), item.getItemColorId(), item.getDrawableResId()), getCircularRevealHandler(index));
        }
    }

    private ControllerChangeHandler getCircularRevealHandler(int index) {
        View circularRevealView = recyclerView.getChildAt(index).findViewById(R.id.action_icon);
        return circularRevealView != null ? new CircularRevealChangeHandlerCompat(circularRevealView, container) : new CircularRevealChangeHandlerCompat();
    }

    private void showDetail(Controller controller, ControllerChangeHandler controllerChangeHandler) {
        if (detailContainer != null) {
            getChildRouter(detailContainer, null).setRoot(RouterTransaction.with(controller));
        } else {
            getRouter().pushController(RouterTransaction.with(controller)
                    .pushChangeHandler(controllerChangeHandler)
                    .popChangeHandler(controllerChangeHandler));
        }
    }

    @Override
    public void addItem(@NotNull Item item) {
        itemAdapter.addItem(item);
    }
}