package com.example.plainoldandroidmaster_detail.core.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.plainoldandroidmaster_detail.TraditionalApp;

import be.vergauwen.simon.common.mvp.MVPContract;
import be.vergauwen.simon.common.mvp.MVPContract.Component;
import be.vergauwen.simon.common.mvp.MVPContract.Presenter;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

public abstract class MVPFragment<V extends MVPContract.View, P extends Presenter<V>, C extends Component<V, P>> extends Fragment implements MVPContract.View {
    protected P presenter;
    protected C component;

    protected abstract C createComponent();

    private P createPresenter() {
        return component.getPresenter();
    }
    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        component = createComponent();
        presenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter.attachView((V) this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Icepick.restoreInstanceState(this,savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this,outState);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        unbinder.unbind();
        unbinder = null;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        ((TraditionalApp) getActivity().getApplication()).refWatcher.watch(this);
        super.onDestroy();
    }
}