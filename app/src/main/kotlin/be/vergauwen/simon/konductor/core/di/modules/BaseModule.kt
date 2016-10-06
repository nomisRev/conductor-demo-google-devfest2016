package be.vergauwen.simon.konductor.core.di.modules

import android.support.v7.app.AppCompatActivity
import be.vergauwen.simon.konductor.core.di.activity.ActionBarProvider
import be.vergauwen.simon.konductor.core.di.activity.ActivityActionBar
import be.vergauwen.simon.konductor.core.di.scopes.ViewScope
import dagger.Module
import dagger.Provides

@Module
class BaseModule() {

    @ViewScope
    @Provides
    fun provideActionBarProvider(appCompatActivity: AppCompatActivity): ActionBarProvider = ActivityActionBar(appCompatActivity)
}