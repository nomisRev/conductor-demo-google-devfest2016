package be.vergauwen.simon.conductor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import be.vergauwen.simon.common.di.component.ActivityComponent;
import be.vergauwen.simon.common.di.component.DaggerActivityComponent;
import be.vergauwen.simon.common.di.modules.ActivityModule;
import be.vergauwen.simon.common.ui.layout.LayoutInjector;
import be.vergauwen.simon.conductor.ui.controllers.MasterViewControllerMVP;

public class MainActivity extends AppCompatActivity {

    ChangeHandlerFrameLayout container;

    public ActivityComponent component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();

    private LayoutInjector<MainActivity> layoutInjector = new MainLayout();
    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutInjector.injectLayout(this));

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new MasterViewControllerMVP()));
        }
    }

    @Override
    public void onBackPressed() {
        if (router != null && !router.handleBack()) {
            super.onBackPressed();
        }
    }
}
