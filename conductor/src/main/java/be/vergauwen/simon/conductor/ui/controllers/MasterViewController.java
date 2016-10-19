package be.vergauwen.simon.conductor.ui.controllers;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.RouterTransaction;

import org.jetbrains.annotations.NotNull;

import be.vergauwen.simon.common.di.data.RxDataProvider;
import be.vergauwen.simon.common.di.data.RxDataRepo;
import be.vergauwen.simon.common.di.model.Item;
import be.vergauwen.simon.common.ui.widget.util.OnItemClickListener;
import be.vergauwen.simon.common.ui.widget.util.RecyclerViewExtKt;
import be.vergauwen.simon.conductor.R;
import be.vergauwen.simon.common.di.changehandlers.CircularRevealChangeHandlerCompat;
import be.vergauwen.simon.conductor.ui.adapter.ItemAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

public class MasterViewController extends Controller {

    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.detailContainer)
    ViewGroup detailContainer;

    private ItemAdapter itemAdapter;
    private Unbinder unbinder;
    private RxDataProvider dataProvider = new RxDataRepo();

    public MasterViewController() {
        super();
    }

    public MasterViewController(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater,
                                @NonNull ViewGroup container) {
        final View view = inflater.inflate(R.layout.master_list_controller, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
        RecyclerViewExtKt.addItemClickListener(recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull RecyclerView.ViewHolder view, int position) {
                onRowSelected(position);
            }
        });
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        dataProvider.getData().forEach(new Action1<Item>() {
            @Override
            public void call(Item item) {
                itemAdapter.addItem(item);
            }
        });
    }

    private void onRowSelected(int index) {
        Item item = itemAdapter.getItem(index);
        if (item != null) {
            showDetail(
                    new DetailViewController(item.getName(), item.getItemColorId(), item.getDrawableResId()),
                    getCircularRevealHandler(index)
            );
        }
    }

    private ControllerChangeHandler getCircularRevealHandler(int index) {
        View circularRevealView = recyclerView.getChildAt(index).findViewById(R.id.action_icon);
        return circularRevealView != null ?
                new CircularRevealChangeHandlerCompat(circularRevealView, container) :
                new CircularRevealChangeHandlerCompat();
    }

    private void showDetail(Controller controller, ControllerChangeHandler controllerChangeHandler) {
        if (detailContainer != null) {
            getChildRouter(detailContainer, null).setRoot(
                    RouterTransaction.with(controller)
            );
        } else {
            getRouter().pushController(
                    RouterTransaction.with(controller)
                            .pushChangeHandler(controllerChangeHandler)
                            .popChangeHandler(controllerChangeHandler)
            );
        }
    }

    @Override
    protected void onDestroyView(View view) {
        unbinder.unbind();
        super.onDestroyView(view);
    }
}
