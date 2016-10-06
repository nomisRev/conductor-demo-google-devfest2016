package be.vergauwen.simon.konductor.core.di.modules

import android.support.v7.app.AppCompatActivity
import be.vergauwen.simon.konductor.core.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityScope
    @Provides
    fun provideAppCompatActivity(): AppCompatActivity = activity
}