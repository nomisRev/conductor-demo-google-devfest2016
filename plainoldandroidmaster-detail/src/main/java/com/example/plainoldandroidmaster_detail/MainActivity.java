package com.example.plainoldandroidmaster_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.plainoldandroidmaster_detail.ui.view.MasterFragment;

import be.vergauwen.simon.common.di.component.ActivityComponent;
import be.vergauwen.simon.common.di.component.DaggerActivityComponent;
import be.vergauwen.simon.common.di.modules.ActivityModule;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public ActivityComponent component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, MasterFragment.newInstance(), MasterFragment.FRAGMENT_TAG)
                    .commit();
    }
}
