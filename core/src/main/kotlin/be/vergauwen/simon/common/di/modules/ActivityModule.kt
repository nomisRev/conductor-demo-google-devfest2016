package be.vergauwen.simon.common.di.modules

import android.support.v7.app.AppCompatActivity
import be.vergauwen.simon.common.di.activity.ActionBarProvider
import be.vergauwen.simon.common.di.activity.ActivityActionBar
import be.vergauwen.simon.common.di.scopes.ActivityScope
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