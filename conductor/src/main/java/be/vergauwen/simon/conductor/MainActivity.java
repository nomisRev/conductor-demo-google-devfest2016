package be.vergauwen.simon.conductor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import be.vergauwen.simon.common.di.component.ActivityComponent;
import be.vergauwen.simon.common.di.component.DaggerActivityComponent;
import be.vergauwen.simon.common.di.modules.ActivityModule;
import be.vergauwen.simon.conductor.ui.controllers.MasterDetailController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    ChangeHandlerFrameLayout container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public ActivityComponent component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new MasterDetailController()));
        }
    }

    @Override
    public void onBackPressed() {
        if (router != null && !router.handleBack()) {
            super.onBackPressed();
        }
    }
}
