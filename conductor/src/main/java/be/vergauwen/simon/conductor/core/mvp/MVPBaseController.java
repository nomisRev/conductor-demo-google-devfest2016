package be.vergauwen.simon.conductor.core.mvp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.rxlifecycle.RxController;

import be.vergauwen.simon.common.mvp.MVPContract;
import be.vergauwen.simon.conductor.ConductorApp;
import icepick.Icepick;

public abstract class MVPBaseController<V extends MVPContract.View, P extends MVPContract.Presenter<V>, C extends MVPContract.Component<V,P>>
        extends RxController implements MVPContract.View {

    private Boolean hasExited = false;
    private C component;
    protected ActionBar supportActionBar;
    protected P presenter;

    abstract protected C createComponent();

    protected MVPBaseController() { }
    protected MVPBaseController(Bundle args) {
        super(args);
    }

    protected abstract View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        component = createComponent();
        presenter = component.getPresenter();
        supportActionBar = component.getActionBarProvider().getSupportActionBar();

        View view = inflateView(inflater, container);
        onViewCreated(view);
        return view;
    }

    protected void onViewCreated(@NonNull View view) { }

    @Override
    protected void onAttach(@NonNull View view) {
        presenter.attachView((V) this);
        super.onAttach(view);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        presenter.detachView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this,outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Icepick.restoreInstanceState(this,savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hasExited) ((ConductorApp)getActivity().getApplication()).refWatcher.watch(this);
    }

    @Override
    protected void onChangeEnded(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        super.onChangeEnded(changeHandler, changeType);
        hasExited = !changeType.isEnter;
        if (isDestroyed()) ((ConductorApp)getActivity().getApplication()).refWatcher.watch(this);
    }

    protected String getTitle() {
        return null;
    }
}
