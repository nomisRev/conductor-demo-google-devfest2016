package be.vergauwen.simon.konductor.core.di.component

import android.support.v7.app.AppCompatActivity
import be.vergauwen.simon.konductor.core.di.modules.ActivityModule
import be.vergauwen.simon.konductor.core.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    val appCompatActivity: AppCompatActivity
}