package be.vergauwen.simon.common.di.component

import android.support.v7.app.AppCompatActivity
import be.vergauwen.simon.common.di.activity.ActionBarProvider
import be.vergauwen.simon.common.di.modules.ActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    val appCompatActivity: AppCompatActivity
    val actionBarProvider: ActionBarProvider

}