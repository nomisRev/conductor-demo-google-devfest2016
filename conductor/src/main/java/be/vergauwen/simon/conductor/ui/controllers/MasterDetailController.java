package be.vergauwen.simon.conductor.ui.controllers;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bluelinelabs.conductor.RouterTransaction;

import org.jetbrains.annotations.NotNull;

import be.vergauwen.simon.common.di.model.Item;
import be.vergauwen.simon.common.ui.component.DaggerMasterComponent;
import be.vergauwen.simon.common.ui.component.MasterComponent;
import be.vergauwen.simon.common.ui.contract.MasterContract;
import be.vergauwen.simon.common.ui.presenter.MasterPresenter;
import be.vergauwen.simon.common.ui.widget.util.OnItemClickListener;
import be.vergauwen.simon.common.ui.widget.util.RecyclerViewExtKt;
import be.vergauwen.simon.conductor.MainActivity;
import be.vergauwen.simon.conductor.R;
import be.vergauwen.simon.conductor.core.changehandlers.CircularRevealChangeHandlerCompat;
import be.vergauwen.simon.conductor.core.mvp.MVPBaseController;
import be.vergauwen.simon.conductor.ui.adapter.ItemAdapter;
import butterknife.BindView;
import icepick.State;

public class MasterDetailController extends MVPBaseController<MasterContract.View, MasterPresenter, MasterComponent> implements MasterContract.View {

    @Override
    protected MasterComponent createComponent() {
        return DaggerMasterComponent.builder().activityComponent(((MainActivity) getActivity()).component).build();
    }

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.detailContainer)
    ViewGroup detailContainer;


    @State
    int selectedIndex = 0;
    private boolean twoPaneView;
    private ItemAdapter itemAdapter;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.master_list_controller, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
        RecyclerViewExtKt.addItemClickListener(recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull View view, int position) {
                onRowSelected(position);
            }
        });

        twoPaneView = (detailContainer != null);
        if (twoPaneView) {
            onRowSelected(selectedIndex);
        }
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        presenter.getData();
    }

    void onRowSelected(int index) {
        selectedIndex = index;

        Item item = itemAdapter.getItem(index);
        if (item == null) return;

        ChildController controller = new ChildController(item.getName(), item.getItemColorId(), item.getDrawableResId());

        if (twoPaneView && detailContainer != null) {
            getChildRouter(detailContainer, null).setRoot(RouterTransaction.with(controller));
        } else {
            getRouter().pushController(RouterTransaction.with(controller)
                    .pushChangeHandler(new CircularRevealChangeHandlerCompat(recyclerView.getChildAt(index).findViewById(R.id.action_icon), container))
                    .popChangeHandler(new CircularRevealChangeHandlerCompat(recyclerView.getChildAt(index).findViewById(R.id.action_icon), container))
            );
        }
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
    }

    @Override
    public void addItem(@NotNull Item item) {
        itemAdapter.addItem(item);
    }
}