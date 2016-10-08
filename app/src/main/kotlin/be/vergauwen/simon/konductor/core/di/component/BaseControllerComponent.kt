package be.vergauwen.simon.konductor.core.di.component

import be.vergauwen.simon.konductor.core.di.activity.ActionBarProvider
import be.vergauwen.simon.konductor.core.di.scopes.ViewScope
import dagger.Component

@ViewScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface BaseControllerComponent {
    val actionBarProvider: ActionBarProvider
}