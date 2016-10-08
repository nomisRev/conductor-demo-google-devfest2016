package be.vergauwen.simon.konductor.core.di.modules

import android.support.v7.app.AppCompatActivity
import be.vergauwen.simon.konductor.core.di.activity.ActionBarProvider
import be.vergauwen.simon.konductor.core.di.activity.ActivityActionBar
import be.vergauwen.simon.konductor.core.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Singleton
    @ActivityScope
    @Provides
    fun provideAppCompatActivity(): AppCompatActivity = activity

    @Singleton
    @Provides
    fun provideActionBarProvider(appCompatActivity: AppCompatActivity): ActionBarProvider = ActivityActionBar(appCompatActivity)
}